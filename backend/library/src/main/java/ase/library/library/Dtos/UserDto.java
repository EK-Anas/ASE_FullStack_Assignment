package ase.library.library.Dtos;

import ase.library.library.Entities.Enums.UserRole;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserDto {
    private long id;
    private String name;
    private String email;
    private String phone;
    @Enumerated
    private UserRole role;
    private String otherInfo;
}
