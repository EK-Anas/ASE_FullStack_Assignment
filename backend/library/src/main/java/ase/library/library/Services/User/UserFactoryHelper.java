package ase.library.library.Services.User;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Entities.User.User;

public class UserFactoryHelper {

    public static User setCommonFields(User user, UserDto userDto) {

        return user
                .setName(userDto.getName())
                .setEmail(userDto.getEmail())
                .setPhone(userDto.getPhone())
                .setRole(userDto.getRole());
    }

    public static UserDto toDto(User user) {

        return new UserDto()
                .setId(user.getId())
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .setRole(user.getRole());
    }

    public static UserFactory getFactory(UserRole userRole) {

        switch (userRole) {
            case STUDENT:
                return new StudentFactory();
            case TEACHER:
                return new TeacherFactory();
            default:
                throw new IllegalArgumentException("invalid user role");
        }
    }

}
