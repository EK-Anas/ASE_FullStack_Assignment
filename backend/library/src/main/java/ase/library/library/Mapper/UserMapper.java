package ase.library.library.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Entities.User.Student;
import ase.library.library.Entities.User.Teacher;
import ase.library.library.Entities.User.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.Collection;
import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface UserMapper {
    @Mapping(target = "department", ignore = true)
    UserDto toStudentDto(Student student);

    @Mapping(target = "course", ignore = true)
    UserDto toTeacherDto(Teacher teacher);

    List<UserDto> toStudentDtos(Collection<Student> students);

    List<UserDto> toTeacherDtos(Collection<Teacher> teachers);

    List<UserDto> toDtos(Collection<User> users);

    default UserDto toDto(User user) {
        return user.getRole() == UserRole.STUDENT
                ? toStudentDto((Student) user)
                : toTeacherDto((Teacher) user);

    }

}
