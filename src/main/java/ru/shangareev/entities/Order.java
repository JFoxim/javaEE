package ru.shangareev.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name ="user_id")
    private int userId;

    @NotNull
    @Column(name ="price")
    private double price;

    @Column(name="phone_number")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

    @NotNull
    @Column(name="status_id")
    private int statusId;

    @NotNull
    @Column(name="create_at")
    private LocalDateTime createAt;

    @NotNull
    @Column(name="update_at")
    private LocalDateTime updateAt;
}

