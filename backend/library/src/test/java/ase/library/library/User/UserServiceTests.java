package ase.library.library.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Entities.User.Teacher;
import ase.library.library.Entities.User.User;
import ase.library.library.Mapper.UserMapper;
import ase.library.library.Repos.UserRepo;
import ase.library.library.Services.User.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserDto mockStudentUser;
    private User mockTeacherUser;

    @BeforeEach
    public void beforeEach() {
        mockStudentUser = new UserDto()
                .setCourse("IT")
                .setEmail("testuser@example.com")
                .setPhone("1234567890")
                .setName("Test")
                .setRole(UserRole.STUDENT);

        mockTeacherUser = new Teacher()
                .setDepartment("cs")
                .setEmail("55testuser@example.com")
                .setPhone("123456789055")
                .setName("Test555")
                .setRole(UserRole.STUDENT);

    }

    @Test
    public void addUser_shouldWork() {

        when(userRepo.existsByEmailOrPhone(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        when(userRepo.save(Mockito.any(User.class))).thenReturn(mockTeacherUser.setId(5L));

        userService.addUser(mockStudentUser);

        Assertions.assertThat(mockStudentUser.getId()).isNotNull();
    }

    @Test
    public void deleteUser_shouldWork() {

        when(userRepo.existsById(Mockito.anyLong())).thenReturn(true);

        userService.deleteUser(5);

        Mockito.verify(userRepo, Mockito.times(1)).deleteById(5L);
    }

    @Test
    public void getUserById_returnsDto() {

        when(userRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(mockTeacherUser));
        when(userMapper.toDto(Mockito.any(User.class))).thenReturn(mockStudentUser);

        var dto = userService.getUserById(50L);

        assertNotNull(dto);
    }

}
