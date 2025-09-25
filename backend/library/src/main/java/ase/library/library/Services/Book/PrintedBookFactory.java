package ase.library.library.Services.Book;

import ase.library.library.Dtos.Book.BookDto;
import ase.library.library.Entities.Book.Book;
import ase.library.library.Entities.Book.PrintedBook;

public class PrintedBookFactory implements BookFactory {

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new PrintedBook()
                .setCopiesAvailable(bookDto.getCopiesAvailable())
                .setShelfLocation(bookDto.getShelfLocation());

        BookFactoryHelper.setCommonFields(book, bookDto);
        return book;

    }

    @Override
    public Book updateBook(Book book, BookDto bookDto) {

        ((PrintedBook) book)
                .setCopiesAvailable(bookDto.getCopiesAvailable())
                .setShelfLocation(bookDto.getShelfLocation());

        BookFactoryHelper.setCommonFields(book, bookDto);
        return book;
    }

    @Override
    public BookDto toDto(Book book) {

        PrintedBook ebook = (PrintedBook) book;

        return BookFactoryHelper
                .toDto(book)
                .setCopiesAvailable(ebook.getCopiesAvailable())
                .setShelfLocation(ebook.getShelfLocation());

    }

}
