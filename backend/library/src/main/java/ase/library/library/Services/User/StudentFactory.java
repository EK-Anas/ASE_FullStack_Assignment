package ase.library.library.Services.User;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.User.Student;
import ase.library.library.Entities.User.User;

public class StudentFactory implements UserFactory {

    @Override
    public User createUser(UserDto userDto) {
        User user = new Student()
                .setCourse(userDto.getCourse());

        UserFactoryHelper.setCommonFields(user, userDto);

        return user;
    }

    @Override
    public User updateUser(User user, UserDto userDto) {

        ((Student) user).setCourse(userDto.getCourse());
        UserFactoryHelper.setCommonFields(user, userDto);
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        Student student = (Student) user;
        return UserFactoryHelper
                .toDto(user)
                .setCourse(student.getCourse());
    }

}
