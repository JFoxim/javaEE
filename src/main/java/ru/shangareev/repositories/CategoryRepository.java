package ru.shangareev.repositories;

import lombok.NoArgsConstructor;
import ru.shangareev.entities.Category;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@NoArgsConstructor
@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    protected EntityManager entityManager;

    @Transactional
    public void merge(Category category){
        entityManager.merge(category);
    }

    @Transactional
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Transactional
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories = entityManager.createQuery(
                "select category from Category as category")
                .getResultList();
        return categories;
    }

    @Transactional
    public void delete(Category category)  {
          entityManager.remove(category);
    }
}
