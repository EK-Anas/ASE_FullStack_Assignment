package ase.library.library.Services.Book;

import ase.library.library.Dtos.Book.BookDto;
import ase.library.library.Entities.Book.Book;
import ase.library.library.Entities.Book.EBook;

public class BookFactoryHelper {
    public static Book setCommonFields(Book book, BookDto bookDto) {

        return book
                .setAuthor(bookDto.getAuthor())
                .setCategory(bookDto.getCategory())
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle());
    }

    public static BookDto toDto(Book book) {

        return new BookDto()
                .setId(book.getId())
                .setAuthor(book.getAuthor())
                .setCategory(book.getCategory())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle());
    }

    public static BookFactory getFactory(boolean isEbook) {
        return isEbook ? new EbookFactory() : new PrintedBookFactory();
    }

    public static BookFactory getFactory(Book book) {
        Boolean isEbook = book instanceof EBook;
        return getFactory(isEbook);
    }
}
