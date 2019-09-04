package ru.shangareev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="name")
    @NotNull(message="Поле не должно быть пустым")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name="price")
    @Digits(integer = 20, fraction = 3)
    private double price;

    @ManyToOne
    private Category category;

    @Override
    public String toString(){
        return this.name;
    }

}

