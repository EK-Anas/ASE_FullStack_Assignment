package ase.library.library.Services.User;

import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.User.User;
import ase.library.library.Exception.BadRequestException;
import ase.library.library.Exception.ResourceNotFoundException;
import ase.library.library.Mapper.UserMapper;
import ase.library.library.Repos.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepo usersRepo;
    private final UserMapper userMapper;

    public void addUser(UserDto userDto) {

        if (usersRepo.existsByEmailOrPhone(userDto.getEmail(), userDto.getPhone())) {
            throw new BadRequestException("Email & Phone must be unique");
        }
        UserFactory userFactory = UserFactoryHelper.getFactory(userDto.getRole());

        User user = userFactory.createUser(userDto);

        usersRepo.save(user);

    }

    public void updateUser(long userId, UserDto userDto) {

        UserFactory userFactory = UserFactoryHelper.getFactory(userDto.getRole());

        User user = usersRepo
                .findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("invalid user id"));

        user = userFactory.updateUser(user, userDto);

        usersRepo.save(user);

    }

    public void deleteUser(long id) {
        if (!usersRepo.existsById(id)) {
            throw new ResourceNotFoundException("invalid user id");
        }
        usersRepo.deleteById(id);
    }

    public UserDto getUserById(long id) {

        User user = usersRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("invalid user id"));

        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        var users = usersRepo
                .findAllByOrderByIdDesc();

        return userMapper.toDtos(users);

    }

}
