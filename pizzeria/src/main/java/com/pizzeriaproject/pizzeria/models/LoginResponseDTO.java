package com.pizzeriaproject.pizzeria.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponseDTO {
    private User user;
    private String jwt;
}
