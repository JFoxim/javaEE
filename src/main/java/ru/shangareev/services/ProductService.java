package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Product;
import ru.shangareev.repositories.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("productService")
public class ProductService implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    private Product product;

    private List<Product> productList;

    public void preloadProductList(ProductRepository productRepository) {

        this.productRepository = productRepository;
        try {
            productList = productRepository.getAllProducts();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
    }

    public List<Product> getProductList()
    {
        List<Product> productList = new ArrayList<>();

        try{
            productList = productRepository.getAllProducts();
        }
        catch (SQLException ex){
            logger.info(ex.getMessage());
        }
       return productList;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void deleteProduct(Product product){
        try {
            productRepository.delete(product);
        }
        catch (SQLException ex){
          logger.info(ex.getMessage());
        }
    }

    public void addProduct(Product product) {
        try {
             productRepository.insert(product);
             this.product = product;
        }catch (SQLException ex){
            logger.info(ex.getMessage());
        }
    }

    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
            this.product = product;
        }catch (SQLException ex){
            logger.info(ex.getMessage());
        }
    }

    public void getProductById(int productId) {
        try {
            this.product = productRepository.findById(productId);
        }catch (SQLException ex){
            logger.info(ex.getMessage());
        }
    }
}
