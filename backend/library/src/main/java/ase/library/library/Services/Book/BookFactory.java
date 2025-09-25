package ase.library.library.Services.Book;

import ase.library.library.Dtos.Book.BookDto;
import ase.library.library.Entities.Book.Book;

public interface BookFactory {

    Book createBook(BookDto BookDto);

    Book updateBook(Book Book, BookDto BookDto);

    BookDto toDto(Book Book);
}
