package ase.library.library.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ase.library.library.Entities.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
