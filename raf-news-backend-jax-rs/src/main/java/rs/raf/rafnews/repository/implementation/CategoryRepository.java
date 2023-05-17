package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.database.RafNewsDatabase;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.dto.ServiceUserDto;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequestScoped
public class CategoryRepository implements ICategoryRepository {
    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        String query = "SELECT * FROM category WHERE id = ?";
        try (PreparedStatement preparedStatement = RafNewsDatabase.getInstance().getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Integer columnId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    return new CategoryDto(columnId, name, description);
                }
                else{
                    return null;
                }
            }
            catch (SQLException e){
                return null;
            }
        }
        catch (SQLException e) {
            return null;
        }
    }

    @Override
    public CategoryDto addCategory(Category category) {
        return null;
    }

    @Override
    public CategoryDto updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        return false;
    }
}
