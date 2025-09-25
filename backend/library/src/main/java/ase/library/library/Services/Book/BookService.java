package ase.library.library.Services.Book;

import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Dtos.Book.BookDto;

import ase.library.library.Entities.Book.Book;

import ase.library.library.Exception.BadRequestException;
import ase.library.library.Exception.ResourceNotFoundException;
import ase.library.library.Repos.BookRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public void addBook(BookDto bookDto) {

        if (bookRepo.existsByIsbn(bookDto.getIsbn()))
            throw new BadRequestException("ISBN must be unique");

        BookFactory factory = BookFactoryHelper.getFactory(bookDto.isEbook());

        Book book = factory.createBook(bookDto);

        bookRepo.save(book);
    }

    public void deleteBook(long id) {

        if (!bookRepo.existsById(id))
            throw new ResourceNotFoundException("invalid id");

        bookRepo.deleteById(id);
    }

    public void updateBook(long id, BookDto bookDto) {

        BookFactory factory = BookFactoryHelper.getFactory(bookDto.isEbook());

        Book book = bookRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("invalid book id"));

        book = factory.updateBook(book, bookDto);

        bookRepo.save(book);
    }

    public List<BookDto> GetAll() {
        var books = bookRepo.findAllByOrderByIdDesc();

        return books
                .stream()
                .map(book -> {
                    BookFactory factory = BookFactoryHelper.getFactory(book);
                    return factory.toDto(book);
                })
                .toList();
    }

    public BookDto GetById(long id) {

        Book book = bookRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("invalid book id"));

        BookFactory factory = BookFactoryHelper.getFactory(book);

        return factory.toDto(book);
    }

}
