package rs.raf.rafnews.repository.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> getAllRawCategories() throws JsonProcessingException, GetException;
    List<CategoryDto> getAllCategories() throws GetException, JsonProcessingException;
    CategoryDto joinCategory(Category category);
    Category getRawCategoryById(Integer id) throws JsonProcessingException, GetException;
    CategoryDto getCategoryById(Integer id) throws JsonProcessingException, GetException;
    CategoryDto addCategory(Category category);
    Integer updateCategoryById(Integer id, Category category);
    Integer deleteCategoryById(Integer id);
}