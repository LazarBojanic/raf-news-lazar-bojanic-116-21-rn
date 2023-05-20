package rs.raf.rafnews.repository.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.exception.ExceptionMessage;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CategoryRepository implements ICategoryRepository {

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
    public CategoryDto addCategory(Category category) {
        return null;
    }

    @Override
    public Integer updateCategoryById(Integer id, Category category) {
        return 0;
    }

    @Override
    public Integer deleteCategoryById(Integer id) {
        return 0;
    }
    private Category extractCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Integer columnId = resultSet.getInt("id");
        String columnCategoryName = resultSet.getString("category_name");
        String columnDescription = resultSet.getString("description");
        return new Category(columnId, columnCategoryName, columnDescription);
    }
}
