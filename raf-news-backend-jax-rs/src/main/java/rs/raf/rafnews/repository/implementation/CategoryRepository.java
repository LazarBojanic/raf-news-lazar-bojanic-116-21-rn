package rs.raf.rafnews.repository.implementation;

import jakarta.enterprise.context.RequestScoped;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;

import java.util.List;

@RequestScoped
public class CategoryRepository implements ICategoryRepository {
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategoryById(Integer id) {
        return null;
    }

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        return false;
    }
}
