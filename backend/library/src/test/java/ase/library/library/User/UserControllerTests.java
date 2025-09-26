package ase.library.library.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import ase.library.library.LibraryApiApplication;
import ase.library.library.Dtos.User.UserDto;
import ase.library.library.Entities.Enums.UserRole;
import ase.library.library.Entities.User.Teacher;
import ase.library.library.Repos.UserRepo;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = LibraryApiApplication.class)
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void userController_getById_returnsDto() throws Exception {

        var mockTeacherUser = new Teacher()
                .setDepartment("cs")
                .setEmail("55testuser@example.com")
                .setPhone("123456789055")
                .setName("Test555")
                .setRole(UserRole.TEACHER);

        userRepo.save(mockTeacherUser);

        var mvcResult = mockMvc.perform(get("/users/" + mockTeacherUser.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var userDto = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                UserDto.class);

        then(userDto)
                .isNotNull();

    }

}
