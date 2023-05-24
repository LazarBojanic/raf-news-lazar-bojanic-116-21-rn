package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllRawCategories() throws GetException, JsonProcessingException, SQLException;
    List<CategoryDto> getAllCategories() throws GetException, JsonProcessingException, SQLException;
    CategoryDto joinCategory(Category category);
    Category getRawCategoryById(Integer id) throws GetException, JsonProcessingException, SQLException;
    CategoryDto getCategoryById(Integer id) throws GetException, JsonProcessingException, SQLException;
    Category getRawCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException;
    CategoryDto getCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException;
    Category addRawCategory(Category category) throws SQLException, AddException, JsonProcessingException;
    CategoryDto addCategory(Category category) throws SQLException, AddException, JsonProcessingException;
    Integer updateCategoryById(Integer id, Category category) throws SQLException, UpdateException, JsonProcessingException;
    Integer deleteCategoryById(Integer id) throws SQLException, DeleteException, JsonProcessingException;
}