package ru.shangareev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     @NotNull(message="Поле не должно быть пустым")
     @Column(name ="title", unique = true, nullable = false)
     private String title;

    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Product> products;
}

