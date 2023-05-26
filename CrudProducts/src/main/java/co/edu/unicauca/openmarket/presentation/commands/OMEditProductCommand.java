/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.presentation.commands;

import co.edu.unicauca.openmarket.domain.Product;
import co.edu.unicauca.openmarket.domain.service.ProductService;
import java.util.List;

/**
 *
 * @author RodAlejo
 */
public class OMEditProductCommand extends OMCommand {

    private Product pP;
    
    private String oldName;
    private String oldDes;
    private String newName;
    private String newDes;
    Product oldProduct;
    private ProductService pS;
    boolean result=false;
    
    public OMEditProductCommand(Product pP,  ProductService pS){
        this.pP = pP;
        this.pS = pS;
        this.newName = pP.getName();
        this.newDes = pP.getDescription();
        this.oldProduct = pS.findProductById(pP.getProductId());
    }
    
    
    @Override
    public void make() {
        oldName = oldProduct.getName();
        oldDes = oldProduct.getDescription();
        result = pS.editProduct(pP.getProductId(), new Product(0L, newName, newDes, 0));
    }

    @Override
    public void unmake() {
        result = pS.editProduct(pP.getProductId(), new Product(0L, oldName, oldDes, 0));
    }
    
    
    public boolean result(){
        return result;
    }

}
