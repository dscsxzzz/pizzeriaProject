package com.pizzeriaproject.pizzeria.models.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String address;
    //private String jwt;
}
