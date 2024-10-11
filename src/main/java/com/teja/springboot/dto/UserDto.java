package com.teja.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @Schema(
            description = "User first name"
    )
    @NotBlank(message = "firstName should not be blank")
    private String firstName;
    @Schema(
            description = "User last name"
    )
    @NotBlank(message = "lastName should not be blank")
    private String lastName;
    @Schema(
            description = "User email address"
    )
    @NotNull
    @Email(message = "Email should be valid")
    private String email;
}
