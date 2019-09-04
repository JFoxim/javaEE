package ru.shangareev.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private int id;

    private String login;

    private String password;
}
