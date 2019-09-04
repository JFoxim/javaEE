package ru.shangareev.dto;

import lombok.*;
import ru.shangareev.entities.Category;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    @NotNull
    private int id;

    @NotNull(message = "Поле не должно быть пустым")
    private String name;

    private String description;

    @NotNull
    @Digits(integer = 20, fraction = 3)
    private double price;

    private CategoryDto categoryDto;

    public ProductDto(int id, String name, String description, double price, Category cat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        CategoryDto categoryDto = new CategoryDto(cat.getId(), cat.getTitle());
        setCategoryDto(categoryDto);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
