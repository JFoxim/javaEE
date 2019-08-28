package ru.shangareev.repositories;

import lombok.NoArgsConstructor;
import ru.shangareev.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@NoArgsConstructor
@ApplicationScoped
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;


    public void merge(Product product){
        entityManager.merge(product);
    }

    public Product findByName(String name) throws SQLException {
        Product product = (Product) entityManager.createQuery(
                "select product from Product as product where product.name = ?1")
                .setParameter(1, name)
                .getSingleResult();
        return product;
    }

    public Product findById(int id) throws SQLException {
        return entityManager.find(Product.class, id);
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        productList = entityManager.createQuery(
                "select product from Product as product")
                .getResultList();
        return productList;
    }

    public void delete(Product product) throws SQLException {
           entityManager.remove(product);
    }

}
