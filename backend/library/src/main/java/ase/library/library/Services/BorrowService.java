package ase.library.library.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Entities.BorrowRecord;
import ase.library.library.Entities.Book.Book;
import ase.library.library.Entities.Book.PrintedBook;
import ase.library.library.Exception.BadRequestException;
import ase.library.library.Exception.ResourceNotFoundException;
import ase.library.library.Repos.BookRepo;
import ase.library.library.Repos.BorrowRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;

    public List<BorrowRecord> GetAllBorrowRecords() {
        return borrowRepo.findAllByOrderByIdDesc();
    }

    @Transactional
    public void borrowBook(long bookId, long userId) {

        Book book = bookRepo
                .findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("invalid book id"));

        if (!(book instanceof PrintedBook))
            throw new BadRequestException("only printed books can be borrowed");

        PrintedBook printedBook = (PrintedBook) book;

        if (printedBook.getCopiesAvailable() == 0)
            throw new BadRequestException("no copies available");

        var borrow = new BorrowRecord()
                .setBookId(bookId)
                .setUserId(userId);

        printedBook.setCopiesAvailable(printedBook.getCopiesAvailable() - 1);
        borrowRepo.save(borrow);
    }

    @Transactional
    public void returnBook(long borrowId, long userId) {

        BorrowRecord record = borrowRepo
                .findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("invalid borrow id"));

        if (record.getReturnDate() != null)
            throw new BadRequestException("book already returned");

        PrintedBook book = (PrintedBook) bookRepo
                .findById(record.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("invalid book id"));

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        record.setReturnDate(LocalDate.now());
    }
}
