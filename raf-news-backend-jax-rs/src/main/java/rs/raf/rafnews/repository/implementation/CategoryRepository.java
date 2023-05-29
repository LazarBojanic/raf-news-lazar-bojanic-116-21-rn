package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.*;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.model.ServiceUser;
import rs.raf.rafnews.repository.specification.ICategoryRepository;
import rs.raf.rafnews.request.CategorySearchRequest;
import rs.raf.rafnews.service.specification.IArticleService;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class CategoryRepository implements ICategoryRepository {
    @Inject
    private IArticleService articleService;
    @Override
    public List<Category> getAllRawCategories() throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    Category category = extractCategoryFromResultSet(resultSet);
                    categoryList.add(category);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return categoryList;
    }
    @Override
    public List<CategoryDto> getAllCategories() throws GetException, JsonProcessingException, SQLException {
        List<Category> categoryList = getAllRawCategories();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category : categoryList){
            categoryDtoList.add(joinCategory(category));
        }
        return categoryDtoList;
    }

    @Override
    public List<CategoryDto> getAllCategoriesFiltered(CategorySearchRequest categorySearchRequest) throws GetException, JsonProcessingException, SQLException {
        List<CategoryDto> categoryDtoList = getAllCategories();
        return categoryDtoList.stream()
                .skip((long) (categorySearchRequest.getPage() - 1) * categorySearchRequest.getPage_size())
                .limit(categorySearchRequest.getPage_size())
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto joinCategory(Category category) {
        return new CategoryDto(category);
    }

    @Override
    public Category getRawCategoryById(Integer id) throws JsonProcessingException, GetException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM category WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractCategoryFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return new Category();
    }
    @Override
    public CategoryDto getCategoryById(Integer id) throws JsonProcessingException, GetException, SQLException {
        Category category = getRawCategoryById(id);
        return joinCategory(category);
    }

    @Override
    public Category getRawCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "SELECT * FROM category WHERE category_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, categoryName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return extractCategoryFromResultSet(resultSet);
                }
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("GetException", e.getMessage());
            throw new GetException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return new Category();
    }

    @Override
    public CategoryDto getCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException {
        Category category = getRawCategoryByCategoryName(categoryName);
        return new CategoryDto(category);
    }

    @Override
    public Category addRawCategory(Category category) throws SQLException, JsonProcessingException, AddException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        try{
            String query = "INSERT INTO category(category_name, description) VALUES(?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, category.getCategory_name());
                preparedStatement.setString(2, category.getDescription());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int id = generatedKeys.getInt("id");
                            category.setId(id);
                            return category;
                        }
                    }
                }
            }
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", "Failed to add category: " + category);
            throw new AddException(exceptionMessage);
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("AddException", e.getMessage());
            throw new AddException(exceptionMessage);
        }
        finally {
            connection.close();
        }
    }

    @Override
    public CategoryDto addCategory(Category category) throws SQLException, AddException, JsonProcessingException {
        Category addedRawCategory = addRawCategory(category);
        return joinCategory(addedRawCategory);
    }

    @Override
    public Integer updateCategoryById(Integer id, Category category) throws SQLException, JsonProcessingException, UpdateException {
        try {
            Category currentCategory = getRawCategoryById(id);
            if(currentCategory.getId() > 0){
                Class<?> categoryClass = Category.class;
                Field[] fields = categoryClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object newValue = field.get(category);
                    if (newValue != null) {
                        field.set(currentCategory, newValue);
                    }
                }
                return saveCategory(currentCategory);
            }
            else{
                ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update category with id: " + id + " with new category: " + category);
                throw new UpdateException(exceptionMessage);
            }
        }
        catch (Exception e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", "Failed to update category with id: " + id + " with new category: " + category + ". " + e.getMessage());
            throw new UpdateException(exceptionMessage);
        }
    }

    @Override
    public Integer saveCategory(Category category) throws SQLException, UpdateException, JsonProcessingException {
        Connection connection = RafNewsDatabase.getInstance().getConnection();
        String query = "UPDATE category SET category_name = ?, description = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, category.getCategory_name());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(3, category.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows >= 0){
                return affectedRows;
            }
        }
        catch (SQLException e) {
            ExceptionMessage exceptionMessage = new ExceptionMessage("UpdateException", e.getMessage());
            throw new UpdateException(exceptionMessage);
        }
        finally {
            connection.close();
        }
        return -1;
    }

    @Override
    public Integer deleteCategoryById(Integer id) throws JsonProcessingException, DeleteException, SQLException, GetException {
        if(articleService.getRawArticleByCategoryId(id).getId() < 0){
            Connection connection = RafNewsDatabase.getInstance().getConnection();
            try{
                String query = "DELETE FROM category WHERE id = ?";
                try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    preparedStatement.setInt(1, id);
                    return preparedStatement.executeUpdate();
                }
            }
            catch (SQLException e){
                ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", e.getMessage());
                throw new DeleteException(exceptionMessage);
            }
        }
        ExceptionMessage exceptionMessage = new ExceptionMessage("DeleteException", "Failed to delete category. An article with that category exists.");
        throw new DeleteException(exceptionMessage);
    }
    private Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        String columnCategoryName = resultSet.getString("category_name");
        String columnDescription = resultSet.getString("description");
        return new Category(columnId, columnCategoryName, columnDescription);
    }


}
