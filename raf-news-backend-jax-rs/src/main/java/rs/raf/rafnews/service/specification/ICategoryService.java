package rs.raf.rafnews.service.specification;

import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.model.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer id);
    CategoryDto addCategory(Category category);
    CategoryDto updateCategory(Category category);
    boolean deleteCategoryById(Integer id);
}