package rs.raf.rafnews.service.specification;

import rs.raf.rafnews.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    boolean deleteCategoryById(Integer id);
}