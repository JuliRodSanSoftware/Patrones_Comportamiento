package co.edu.unicauca.openmarket.domain.service;

import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import reloj.frameworkobsobs.Observado;
import reloj.frameworkobsobs.Observador;

public class ProductServiceTest {
    @Mock
    private IProductRepository repository;

    private ProductService productService;


    @Test
    public void testSaveProduct_ValidProduct_ReturnsTrue() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(repository);
        
        String name = "Test Product";
        String description = "Test Description";

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);

        Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(true);
        productService.mode = "no";
        boolean result = productService.saveProduct(name, description);

        Assertions.assertTrue(result);
    }

    @Test
    public void testSaveProduct_EmptyName_ReturnsFalse() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(repository);
        String name = "";
        String description = "Test Description";

        boolean result = productService.saveProduct(name, description);

        Assertions.assertFalse(result);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any(Product.class));
    }

    @Test
    public void testFindAllProducts_ReturnsListOfProducts() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(repository);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", "Description 1", 0));
        productList.add(new Product(2L, "Product 2", "Description 2", 0));

        Mockito.when(repository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAllProducts();

        Assertions.assertEquals(productList, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    
    public class ObservadoMock extends Observado {
        @Override
        public void addObservador(Observador one) {
            // No se realiza ninguna operación en el mock
        }

        @Override
        public void notificar() {
            // No se realiza ninguna operación en el mock
        }
    }
    

}