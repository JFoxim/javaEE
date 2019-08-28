package ru.shangareev.repositories;

import lombok.NoArgsConstructor;
import ru.shangareev.entities.Product;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Named
@NoArgsConstructor
@ApplicationScoped
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    @Transactional
    public void merge(Product product){
        entityManager.merge(product);
    }

    @Transactional
    public Product findByName(String name) {
        Product product = (Product) entityManager.createQuery(
                "select product from Product as product where product.name = ?1")
                .setParameter(1, name)
                .getSingleResult();
        return product;
    }

    @Transactional
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList = entityManager.createQuery(
                "select product from Product as product", Product.class)
                .getResultList();
        return productList;
    }

    @Transactional
    public void delete(Product product) {
           entityManager.remove(product);
    }

}
