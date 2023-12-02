package com.pizzeriaproject.pizzeria.models.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ForgotPasswordResponseDTO {
    private String message;
    private Long id;

    public ForgotPasswordResponseDTO(String message) {
        this.message = message;
    }
}
