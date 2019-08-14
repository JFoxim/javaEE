package ru.shangareev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @NotNull
    private int id;

    @NotNull(message="Поле не должно быть пустым")
    private String name;

    private String description;

    @NotNull
    @Digits(integer = 20, fraction = 3)
    private double price;

}

