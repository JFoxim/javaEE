package ru.shangareev.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto implements Serializable {

    @NotNull
    private int id;

    @NotNull(message = "Поле не должно быть пустым")
    private String title;

    private List<ProductDto> products;

    @Override
    public String toString() {
        return this.title;
    }

    public CategoryDto(int id, String title){
        this.id = id;
        this.title = title;
    }

}
