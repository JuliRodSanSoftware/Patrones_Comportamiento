package co.edu.unicauca.openmarket.domain.service;


import co.edu.unicauca.openmarket.access.IProductRepository;
import co.edu.unicauca.openmarket.domain.Product;
import java.util.ArrayList;
import java.util.List;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author Libardo, Julio
 */
public class ProductService extends Observado{

    // Ahora hay una dependencia de una abstracción, no es algo concreto,
    // no sabe cómo está implementado.
    private IProductRepository repository;
    
    public String mode = "default";

    /**
     * Inyección de dependencias en el constructor. Ya no conviene que el mismo
     * servicio cree un repositorio concreto
     *
     * @param repository una clase hija de IProductRepository
     */
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    public boolean saveProduct(String name, String description) {
        
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean respuesta = repository.save(newProduct);
        
        if (mode.equals("default"))
            this.notificar();   
        return respuesta ;
        

    }
    

    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();;

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    
    public Product findProductByName(String name){
        return repository.findByName(name);
    }
    
    public boolean deleteProduct(Long id){
        boolean result;
        result = repository.delete(id);
        this.notificar();
        return result;
    }

    public boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        
        boolean result;
        result = repository.edit(productId, prod); 
        this.notificar();
        return result;
    }
    
    public boolean categorizeProducts(Long categoryId, List<Long> products){
        boolean result = repository.categorizeProducts(categoryId, products);
        this.notificar();
        return result;
    }
    
    public boolean editCategory(Long productId, Long categoryId){
        boolean result = repository.editCategory(productId, categoryId);
        this.notificar();
        return result;
    }

}
