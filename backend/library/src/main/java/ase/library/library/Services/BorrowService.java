package ase.library.library.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import ase.library.library.Entities.BorrowRecord;
import ase.library.library.Entities.Book.PrintedBook;
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

    public boolean BorrowBook(long bookId, long userId) {

        if (!bookRepo.existsById(bookId))
            return false;

        PrintedBook book = (PrintedBook) bookRepo
                .findById(bookId)
                .orElseThrow();

        if (book.getCopiesAvailable() == 0)
            return false;

        var borrow = new BorrowRecord()
                .setBookId(bookId)
                .setUserId(userId);

        book.setCopiesAvailable(book.getCopiesAvailable() - 1);

        bookRepo.save(book);
        borrowRepo.save(borrow);
        return true;
    }

    @Transactional
    public boolean ReturnBook(long burrowId, long userId) {

        BorrowRecord record = borrowRepo
                .findById(burrowId)
                .orElseThrow();

        if (!borrowRepo.existsByBookIdAndUserIdAndReturnDateIsNull(record.getBookId(), userId))
            return false;

        PrintedBook book = (PrintedBook) bookRepo
                .findById(record.getBookId())
                .orElseThrow();

        book.setCopiesAvailable(book.getCopiesAvailable() + 1);

        // var record = borrowRepo.findByBookIdAndUserIdAndReturnDateIsNull(bookId,
        // userId);

        record.setReturnDate(LocalDate.now());

        return true;

    }
}
