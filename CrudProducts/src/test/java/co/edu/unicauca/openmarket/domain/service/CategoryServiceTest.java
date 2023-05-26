package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.ICategoryRepository;
import co.edu.unicauca.openmarket.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceTest {
    private CategoryService categoryService;
    private ICategoryRepository categoryRepository;

   
    @Test
    public void testSaveCategory_ValidCategory_ReturnsTrue() {
        
        categoryRepository = Mockito.mock(ICategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
        
        // Arrange
        String categoryName = "Electronics";
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(true);

        // Act
        boolean result = categoryService.saveCategory(categoryName);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(categoryRepository).save(Mockito.any(Category.class));
    }

    @Test
    public void testSaveCategory_EmptyCategoryName_ReturnsFalse() {
        
        categoryRepository = Mockito.mock(ICategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
        
        // Arrange
        String categoryName = "";
        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(true);

        // Act
        boolean result = categoryService.saveCategory(categoryName);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(categoryRepository, Mockito.never()).save(Mockito.any(Category.class));
    }

    @Test
    public void testFindAllCategories() {
        categoryRepository = Mockito.mock(ICategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
        
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Electronics"));
        categories.add(new Category(2L, "Clothing"));
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.findAllCategories();

        // Assert
        Assertions.assertEquals(categories, result);
        Mockito.verify(categoryRepository).findAll();
    }

    // More test methods for other methods in CategoryService
}