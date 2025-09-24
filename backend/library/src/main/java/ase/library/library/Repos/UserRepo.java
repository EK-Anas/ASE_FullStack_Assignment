package ase.library.library.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ase.library.library.Entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
