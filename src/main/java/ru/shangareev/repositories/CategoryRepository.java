package ru.shangareev.repositories;

import lombok.NoArgsConstructor;
import ru.shangareev.entities.Category;

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
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    public void merge(Category category){
        entityManager.merge(category);
    }

    public Category findById(int id) throws SQLException {
        return entityManager.find(Category.class, id);
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        categories = entityManager.createQuery(
                "select category from Category as category")
                .getResultList();
        return categories;
    }

    public void delete(Category category) throws SQLException {
          entityManager.remove(category);
    }
}
