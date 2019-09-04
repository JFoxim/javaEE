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

    public CategoryService(){ }

    public List<Category> getCategoryList()
    {
        return categoryRepository.getAllCategories();
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void deleteCategory(Category category){
         categoryRepository.delete(category);
     }

    public void addCategory(Category category) {
        categoryRepository.merge(category);
        this.category = category;
    }

    public void saveCategory(Category category) {
            categoryRepository.merge(category);
            this.category = category;
    }

    public void getCategoryById(int categoryId) {
         this.category = categoryRepository.findById(categoryId);
    }
}
