package ru.shangareev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @NotNull
    private int id;

    @NotNull
    private int userId;

    @NotNull
    private double price;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @NotNull
    private int statusId;

    @NotNull
    private LocalDateTime createAt;

    @NotNull
    private LocalDateTime updateAt;
}

