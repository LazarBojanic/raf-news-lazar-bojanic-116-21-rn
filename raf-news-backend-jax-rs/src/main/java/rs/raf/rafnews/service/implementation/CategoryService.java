package rs.raf.rafnews.service.implementation;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;
import rs.raf.rafnews.service.specification.ICategoryService;

import java.util.List;

@RequestScoped
public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.getAllCategories();
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return this.categoryRepository.getCategoryById(id);
    }

    @Override
    public CategoryDto addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    @Override
    public CategoryDto updateCategory(Category category) {
        return this.categoryRepository.updateCategory(category);
    }

    @Override
    public boolean deleteCategoryById(Integer id) {
        return this.categoryRepository.deleteCategoryById(id);
    }
}
