package rs.raf.rafnews.repository.specification;

import rs.raf.rafnews.model.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    boolean deleteCategoryById(Integer id);
}