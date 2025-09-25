package ase.library.library.Services.User;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.User.Teacher;
import ase.library.library.Entities.User.User;

public class TeacherFactory implements UserFactory {

    @Override
    public User createUser(UserDto userDto) {
        User user = new Teacher()
                .setDepartment(userDto.getDepartment());

        UserFactoryHelper.setCommonFields(user, userDto);
        return user;
    }

    @Override
    public User updateUser(User user, UserDto userDto) {
        ((Teacher) user).setDepartment(userDto.getDepartment());
        UserFactoryHelper.setCommonFields(user, userDto);
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        Teacher teacher = (Teacher) user;
        return UserFactoryHelper
                .toDto(user)
                .setDepartment(teacher.getDepartment());
    }

}
