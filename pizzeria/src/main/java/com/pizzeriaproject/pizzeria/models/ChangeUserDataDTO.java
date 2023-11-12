package com.pizzeriaproject.pizzeria.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeUserDataDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String address;
}
