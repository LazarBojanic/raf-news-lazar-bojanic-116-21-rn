package rs.raf.rafnews.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import rs.raf.rafnews.dto.CategoryDto;
import rs.raf.rafnews.exception.AddException;
import rs.raf.rafnews.exception.DeleteException;
import rs.raf.rafnews.exception.GetException;
import rs.raf.rafnews.exception.UpdateException;
import rs.raf.rafnews.model.Category;
import rs.raf.rafnews.repository.specification.ICategoryRepository;
import rs.raf.rafnews.request.CategorySearchRequest;
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
    public List<CategoryDto> getAllCategoriesFiltered(CategorySearchRequest categorySearchRequest) throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getAllCategoriesFiltered(categorySearchRequest);
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
    public Category getRawCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getRawCategoryByCategoryName(categoryName);
    }

    @Override
    public CategoryDto getCategoryByCategoryName(String categoryName) throws GetException, JsonProcessingException, SQLException {
        return this.categoryRepository.getCategoryByCategoryName(categoryName);
    }

    @Override
    public Category addRawCategory(Category category) throws SQLException, AddException, JsonProcessingException {
        return this.categoryRepository.addRawCategory(category);
    }

    @Override
    public CategoryDto addCategory(Category category) throws SQLException, AddException, JsonProcessingException {
        return this.categoryRepository.addCategory(category);
    }

    @Override
    public Integer updateCategoryById(Integer id, Category category) throws SQLException, UpdateException, JsonProcessingException {
        return this.categoryRepository.updateCategoryById(id, category);
    }

    @Override
    public Integer saveCategory(Category category) throws SQLException, UpdateException, JsonProcessingException {
        return this.categoryRepository.saveCategory(category);
    }

    @Override
    public Integer deleteCategoryById(Integer id) throws SQLException, DeleteException, JsonProcessingException, GetException {
        return this.categoryRepository.deleteCategoryById(id);
    }
}
