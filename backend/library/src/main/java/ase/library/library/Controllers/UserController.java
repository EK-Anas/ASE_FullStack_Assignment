package ase.library.library.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ase.library.library.Dtos.UserDto;
import ase.library.library.Entities.User;
import ase.library.library.Services.UserService;
import ase.library.library.Util.Util;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
// @CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("user/{id}")
    public User getMethodName(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);

        return Util.ok();
    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody UserDto user) {

        userService.addUser(user);
        return Util.ok();
    }

    @PutMapping("user")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto user) {

        userService.updateUser(user);
        return Util.ok();
    }

}
