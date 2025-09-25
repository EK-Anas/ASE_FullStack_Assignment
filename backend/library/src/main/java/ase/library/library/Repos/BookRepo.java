package ase.library.library.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ase.library.library.Entities.Book.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

    List<Book> findAllByOrderByIdDesc();

}
