package rs.raf.rafnews.service.specification;

import com.fasterxml.jackson.core.JsonProcessingException;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllRawCategories() throws GetException, JsonProcessingException;
    List<CategoryDto> getAllCategories() throws GetException, JsonProcessingException;
    CategoryDto joinCategory(Category category);
    Category getRawCategoryById(Integer id) throws GetException, JsonProcessingException;
    CategoryDto getCategoryById(Integer id) throws GetException, JsonProcessingException;
    CategoryDto addCategory(Category category);
    Integer updateCategoryById(Integer id, Category category);
    Integer deleteCategoryById(Integer id);
}