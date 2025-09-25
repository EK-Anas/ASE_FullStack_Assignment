package ase.library.library.Dtos.User;

import ase.library.library.Entities.Enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {
    private long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String phone;
    @Enumerated
    private UserRole role;
    private String course;
    private String department;
}
