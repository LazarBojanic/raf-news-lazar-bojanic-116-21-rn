package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;
import rs.raf.rafnews.service.specification.ICategoryService;

import java.sql.SQLException;
import java.util.List;

@RequestScoped
public class CategoryService implements ICategoryService {
    @Inject
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllRawCategories() throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getAllRawCategories();
    }
    @Override
    public List<CategoryDto> getAllCategories() throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getAllCategories();
    }

    @Override
    public CategoryDto joinCategory(Category category) {
        return this.categoryRepository.joinCategory(category);
    }

    @Override
    public Category getRawCategoryById(Integer id) throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getRawCategoryById(id);
    }
    @Override
    public CategoryDto getCategoryById(Integer id) throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getCategoryById(id);
    }

    @Override
    public CategoryDto addCategory(Category category) {
        return this.categoryRepository.addCategory(category);
    }

    @Override
    public Integer updateCategoryById(Integer id, Category category) {
        return this.categoryRepository.updateCategoryById(id, category);
    }

    @Override
    public Integer deleteCategoryById(Integer id) {
        return this.categoryRepository.deleteCategoryById(id);
    }
}
