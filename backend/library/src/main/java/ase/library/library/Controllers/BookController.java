package ase.library.library.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ase.library.library.Dtos.EbookDto;
import ase.library.library.Dtos.PrintedBookDto;
import ase.library.library.Entities.Book;
import ase.library.library.Services.BookService;
import ase.library.library.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("books")
    public List<Book> getBooks() {
        return bookService.GetAll();
    }

    @GetMapping("book/{id}")
    public Book getBookById(@PathVariable long id) {
        return bookService.GetById(id);
    }

    @PostMapping("book/e")
    public ResponseEntity<Void> postEbook(@RequestBody EbookDto ebook) {
        bookService.addEBook(ebook);
        return Util.ok();
    }

    @PostMapping("book/printed")
    public ResponseEntity<Void> postPrinted(@RequestBody PrintedBookDto printedBookDto) {
        bookService.addPrintedBook(printedBookDto);
        return Util.ok();
    }

    @PutMapping("book/e/{id}")
    public ResponseEntity<Void> putBook(@PathVariable long id, @RequestBody EbookDto book) {

        bookService.updateEBook(id, book);
        return Util.ok();
    }

    @PutMapping("book/shelf/{id}")
    public ResponseEntity<Void> putBookPrint(@PathVariable long id, @RequestBody PrintedBookDto book) {

        bookService.updatePrintBook(id, book);
        return Util.ok();
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return Util.ok();
    }

}
