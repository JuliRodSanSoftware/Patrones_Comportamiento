/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RodAlejo
 */
public class OMCategorizeProductCommand  extends OMCommand{

    private List<Long> listPreCategoryId = new ArrayList<>();
    private List<Long> idProducts = new ArrayList<>();
    private long newCategoryId;
    private ProductService pS;
    boolean result=false;
    
    public OMCategorizeProductCommand(Long newCategoryId,  List<Product> listProducts, ProductService productService){
        this.newCategoryId = newCategoryId;

        for (Product product : listProducts) {
            this.idProducts.add(product.getProductId());
            this.listPreCategoryId.add(product.getCategory().getCategoryId());
            
        }
        pS = productService;
        
    }
    
    
    @Override
    public void make() {
        System.out.println("data product "+ newCategoryId +" "+ idProducts.get(0));
        result = pS.categorizeProducts(newCategoryId, idProducts);
    }

    @Override
    public void unmake() {
        for (int i = 0; i< idProducts.size(); i++){
            pS.editCategory(idProducts.get(i),listPreCategoryId.get(i));
            System.out.println("Entró");
        }
    }
    
    
    public boolean result(){
        return result;
    }
    
}
