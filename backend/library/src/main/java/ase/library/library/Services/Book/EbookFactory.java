package ase.library.library.Services.Book;

import ase.library.library.Dtos.Book.BookDto;
import ase.library.library.Entities.Book.Book;
import ase.library.library.Entities.Book.EBook;

public class EbookFactory implements BookFactory {

    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new EBook()
                .setFileSizeMb(bookDto.getFileSizeMb())
                .setFileUrl(bookDto.getFileUrl());

        BookFactoryHelper.setCommonFields(book, bookDto);

        return book;

    }

    @Override
    public Book updateBook(Book book, BookDto bookDto) {

        ((EBook) book)
                .setFileSizeMb(bookDto.getFileSizeMb())
                .setFileUrl(bookDto.getFileUrl());

        BookFactoryHelper.setCommonFields(book, bookDto);
        return book;
    }

    @Override
    public BookDto toDto(Book book) {

        EBook ebook = (EBook) book;

        return BookFactoryHelper
                .toDto(book)
                .setFileSizeMb(ebook.getFileSizeMb())
                .setFileUrl(ebook.getFileUrl());

    }

}
