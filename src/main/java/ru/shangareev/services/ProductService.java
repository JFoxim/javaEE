package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.dto.CategoryDto;
import ru.shangareev.dto.ProductDto;
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
import java.util.stream.Collectors;

@ApplicationScoped
@Named("productService")
public class ProductService implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private ProductDto product;

    private List<ProductDto> productList;

    private List<CategoryDto> categoriesDto;

    public ProductService(){
    }

    @PostConstruct
    public void init(){
        productList = productRepository.getAllProducts().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory()))
                .collect(Collectors.toList());

        categoriesDto = categoryRepository.getAllCategories().stream()
        .map(c -> new CategoryDto(c.getId(), c.getTitle()))
        .collect(Collectors.toList());
    }

    public List<ProductDto> getProductList(){
        productList = productRepository.getAllProducts().stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory()))
                .collect(Collectors.toList());
       return  productList;
    }

    public ProductDto getProduct() {
        productList = productRepository.getAllProducts().stream()
          .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getCategory()))
          .collect(Collectors.toList());
        return this.product;
    }

    public void setProduct(ProductDto productDto) {
        this.product = productDto;
    }

    public String deleteProduct(ProductDto product){
         productRepository.delete(product.getId());
        return "catalog.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        ProductDto prod = new ProductDto();
        prod.setId(-1);
        prod.setName("");
        prod.setPrice(0.0);
        this.product = prod;
        return "product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        System.out.println("save prepare");

        CategoryDto catDto = product.getCategoryDto();

        Category cat = new Category(catDto.getId(), catDto.getTitle());

        Product prod = new Product(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                cat);

        if (prod.getId() == -1) {
            productRepository.add(prod);
        }
        else {
            productRepository.merge(prod);
        }

        System.out.println("save complite");
        System.out.println(product.getName());
        return "catalog.xhtml?faces-redirect=true";
    }

    public String editProduct(ProductDto prod) {
        this.product = prod;
        return "product.xhtml?faces-redirect=true";
    }

    public ProductDto getProductById(int productId) {
         Product prod = productRepository.findById(productId);

         return new ProductDto(prod.getId(),
                 prod.getName(),
                 prod.getDescription(),
                 prod.getPrice(),
                 prod.getCategory());
    }

    public List<CategoryDto> getAllCategories() {
        categoriesDto = categoryRepository.getAllCategories().stream()
                .map(c -> new CategoryDto(c.getId(), c.getTitle()))
                .collect(Collectors.toList());
        return categoriesDto;
    }
}
