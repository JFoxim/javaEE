package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Category;
import ru.shangareev.entities.Product;
import ru.shangareev.repositories.CategoryRepository;
import ru.shangareev.repositories.ProductRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Named("productService")
public class ProductService implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private Product product;

    private List<Product> productList;

    private List<Category> categories;

    public ProductService(){
    }

    @PostConstruct
    public void init(){
        productList = productRepository.getAllProducts();
        categories = categoryRepository.getAllCategories();
    }

    public List<Product> getProductList()    {
       return productList;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String deleteProduct(Product product){
         productRepository.delete(product);
        return "catalog.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        Product product = new Product();
        productRepository.merge(product);
        this.product = product;
        return "product.xhtml?faces-redirect=true";
    }

    public String saveProduct(Product product) {
        productRepository.merge(product);
        this.product = product;
        return "catalog.xhtml?faces-redirect=true";
    }


    public String editProduct(Product product) {
        this.product = product;
        return "product.xhtml?faces-redirect=true";
    }

    public void getProductById(int productId) {
         this.product = productRepository.findById(productId);
    }

    public List<Category> getAllCategories() {
        return categories;
    }
}
