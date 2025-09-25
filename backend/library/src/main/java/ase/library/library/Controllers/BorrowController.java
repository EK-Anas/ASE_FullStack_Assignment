package ase.library.library.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ase.library.library.Entities.BorrowRecord;
import ase.library.library.Services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void borrowBook(@PathVariable Long id) {
        borrowService.borrowBook(id, 3);
    }

    @PutMapping("{borrowId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@PathVariable Long borrowId) {
        borrowService.returnBook(borrowId, 3);
    }

    @GetMapping()
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowService.GetAllBorrowRecords();
    }

}
