package ase.library.library.User;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Entities.User.Student;
import ase.library.library.Entities.User.Teacher;
import ase.library.library.Entities.User.User;
import ase.library.library.Repos.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTests {

    @Autowired
    private UserRepo userRepo;
    private User mockStudentUser;
    private User mockTeacherUser;

    @BeforeEach
    public void beforeEach() {
        mockStudentUser = new Student()
                .setCourse("IT")
                .setEmail("testuser@example.com")
                .setPhone("1234567890")
                .setName("Test")
                .setRole(UserRole.STUDENT);

        mockTeacherUser = new Teacher()
                .setDepartment("cs")
                .setEmail("testuser55@example.com")
                .setPhone("123456789055")
                .setName("Test555")
                .setRole(UserRole.STUDENT);

    }

    @Test
    public void existsByEmailOrPhone_returnsTrue() {

        userRepo.save(mockStudentUser);

        var result = userRepo.existsByEmailOrPhone(mockStudentUser.getEmail(), mockStudentUser.getPhone());
        assertTrue(result);

    }

    @Test
    public void existsByEmailOrPhone_returnsFalse() {

        userRepo.save(mockTeacherUser);

        mockTeacherUser
                .setEmail("newemail@53535")
                .setPhone("353535");

        var result = userRepo.existsByEmailOrPhone(mockStudentUser.getEmail(), mockStudentUser.getPhone());
        assertFalse(result);

    }
}