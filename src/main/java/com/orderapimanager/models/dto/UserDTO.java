package com.orderapimanager.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotNull(message = "Name cannot be null.")
    private String name;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    private String email;
}
