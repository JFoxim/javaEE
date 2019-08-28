package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Product;
import ru.shangareev.repositories.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Named("productService")
public class ProductService implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private ProductRepository productRepository;

    private Product product;

    private List<Product> productList;

    public ProductService(){

    }

    public List<Product> getProductList()
    {
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
             productRepository.merge(product);
             this.product = product;
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
    }

    public void saveProduct(Product product) {
        try {
            productRepository.merge(product);
            this.product = product;
        }catch (Exception ex){
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
