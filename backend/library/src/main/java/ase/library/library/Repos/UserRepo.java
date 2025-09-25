package ase.library.library.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ase.library.library.Entities.User.User;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findAllByOrderByIdDesc();

    Boolean existsByEmailOrPhone(String email, String phone);

}
