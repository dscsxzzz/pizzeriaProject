package com.pizzeriaproject.pizzeria.models.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistrationDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private String address;
}
