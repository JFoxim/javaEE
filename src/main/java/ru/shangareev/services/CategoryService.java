package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.Category;
import ru.shangareev.repositories.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
@Named("categoryService")
public class CategoryService implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categoryList;

    public CategoryService(){

    }

    public List<Category> getCategoryList()
    {
        try{
            categoryList = categoryRepository.getAllCategories();
        }
        catch (SQLException ex){
            logger.info(ex.getMessage());
        }
        return categoryList;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void deleteCategory(Category category){
        try {
            categoryRepository.delete(category);
        }
        catch (SQLException ex){
            logger.info(ex.getMessage());
        }
    }

    public void addCategory(Category category) {
        try {
            categoryRepository.merge(category);
            this.category = category;
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
    }

    public void saveCategory(Category category) {
        try {
            categoryRepository.merge(category);
            this.category = category;
        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
    }

    public void getCategoryById(int categoryId) {
        try {
            this.category = categoryRepository.findById(categoryId);
        }catch (SQLException ex){
            logger.info(ex.getMessage());
        }
    }
}
