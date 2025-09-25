package ase.library.library.Services.User;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.User.User;

public interface UserFactory {

    User createUser(UserDto userDto);

    User updateUser(User user, UserDto userDto);

    UserDto toDto(User user);
}
