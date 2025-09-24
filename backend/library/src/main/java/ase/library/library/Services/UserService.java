package ase.library.library.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Dtos.UserDto;
import ase.library.library.Entities.Student;
import ase.library.library.Entities.Teacher;
import ase.library.library.Entities.User;
import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Repos.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepo usersRepo;

    public void addUser(UserDto userDto) {

        User user = null;
        if (userDto.getRole() == UserRole.STUDENT) {

            user = new Student()
                    .setCourse(userDto.getOtherInfo());
        } else {

            user = new Teacher()
                    .setDepartment(userDto.getOtherInfo());

        }

        user
                .setName(userDto.getName())
                .setEmail(userDto.getEmail())
                .setPhone(userDto.getPhone())
                .setRole(userDto.getRole());

        usersRepo.save(user);

    }

    public void updateUser(UserDto userDto) {

        User user = usersRepo
                .findById(userDto.getId())
                .orElseThrow();

        user
                .setName(userDto.getName())
                .setEmail(userDto.getEmail())
                .setPhone(userDto.getPhone())
                .setRole(userDto.getRole());

        if (user instanceof Student) {

            ((Student) user).setCourse(userDto.getOtherInfo());
        } else {

            ((Teacher) user).setDepartment(userDto.getOtherInfo());
        }
        usersRepo.save(user);

    }

    public void deleteUser(long id) {
        usersRepo.deleteById(id);
    }

    public User getUserById(long id) {

        return usersRepo
                .findById(id)
                .orElseThrow();
    }

    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

}
