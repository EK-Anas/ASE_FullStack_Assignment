package ase.library.library.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Dtos.EbookDto;
import ase.library.library.Dtos.PrintedBookDto;
import ase.library.library.Entities.Book;
import ase.library.library.Entities.EBook;
import ase.library.library.Entities.PrintedBook;
import ase.library.library.Repos.BookRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public void deleteBook(long id) {
        bookRepo.deleteById(id);
    }

    public void addEBook(EbookDto bookDto) {

        Book book = new EBook()
                .setFileSizeMb(bookDto.getFileSizeMb())
                .setFileUrl(bookDto.getFileUrl())
                .setAuthor(bookDto.getAuthor())
                .setCategory(bookDto.getCategory())
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle());

        bookRepo.save(book);

    }

    public void addPrintedBook(PrintedBookDto bookDto) {

        Book book = new PrintedBook()
                .setShelfLocation(bookDto.getShelfLocation())
                .setCopiesAvailable(bookDto.getCopiesAvailable())
                .setAuthor(bookDto.getAuthor())
                .setCategory(bookDto.getCategory())
                .setIsbn(bookDto.getIsbn())
                .setTitle(bookDto.getTitle());

        bookRepo.save(book);

    }

    public List<Book> GetAll() {
        return bookRepo.findAll();
    }

    public Book GetById(long id) {
        return bookRepo
                .findById(id)
                .orElseThrow();
    }

    public void updateEBook(long id, EbookDto ebook) {
        var book = (EBook) GetById(id);

        book
                .setFileSizeMb(ebook.getFileSizeMb())
                .setFileUrl(ebook.getFileUrl())
                .setAuthor(ebook.getAuthor())
                .setCategory(ebook.getCategory())
                .setIsbn(ebook.getIsbn())
                .setTitle(ebook.getTitle());

        bookRepo.save(book);

    }

    public void updatePrintBook(long id, PrintedBookDto ebook) {
        var book = (PrintedBook) GetById(id);

        book
                .setShelfLocation(ebook.getShelfLocation())
                .setCopiesAvailable(ebook.getCopiesAvailable())
                .setAuthor(ebook.getAuthor())
                .setCategory(ebook.getCategory())
                .setIsbn(ebook.getIsbn())
                .setTitle(ebook.getTitle());

        bookRepo.save(book);

    }

    public void updateBook(long id, Object bookDto) {

        Book book = GetById(id);

        if (bookDto instanceof EbookDto) {

            var eBookDto = (EbookDto) bookDto;
            ((EBook) book)
                    .setFileSizeMb(eBookDto.getFileSizeMb())
                    .setFileUrl(eBookDto.getFileUrl())
                    .setAuthor(eBookDto.getAuthor())
                    .setCategory(eBookDto.getCategory())
                    .setIsbn(eBookDto.getIsbn())
                    .setTitle(eBookDto.getTitle());

        } else {

            var shelfDto = (PrintedBookDto) bookDto;
            ((PrintedBook) book)
                    .setCopiesAvailable(shelfDto.getCopiesAvailable())
                    .setShelfLocation(shelfDto.getShelfLocation())
                    .setAuthor(shelfDto.getAuthor())
                    .setCategory(shelfDto.getCategory())
                    .setIsbn(shelfDto.getIsbn())
                    .setTitle(shelfDto.getTitle());

        }

        bookRepo.save(book);

    }
}
