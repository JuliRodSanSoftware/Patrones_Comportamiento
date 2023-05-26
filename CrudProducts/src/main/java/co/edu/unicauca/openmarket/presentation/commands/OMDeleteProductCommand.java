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
public class OMDeleteProductCommand extends OMCommand{
    
    private Product pP;
    private ProductService pS;
    private List<Product> removedProducts = new ArrayList<>();
    boolean result=false;
    public OMDeleteProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }

    @Override
    public void make() {
        Product removedProduct = pS.findProductByName(pP.getName());
        result = pS.deleteProduct(removedProduct.getProductId());
        pP.setName(removedProduct.getName());
        removedProducts.add(removedProduct);
        
        
    }

    @Override
    public void unmake() {
        List<Product> products = removedProducts;
        
        for(Product each: products){
           if(each.getName().equals(pP.getName())){
                System.out.println("Recuperacion de borrado: "+removedProducts.size());

                result = pS.saveProduct(each.getName(), each.getDescription() );
                List<Product> allProducts = pS.findAllProducts();
                int index  = allProducts.size() - 1;
                pP = allProducts.get(index);
                return;
            }
        }
        
    }
    
    
    public boolean result(){
        return result;
    }

}
