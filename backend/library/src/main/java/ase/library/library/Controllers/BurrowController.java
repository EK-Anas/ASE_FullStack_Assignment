package ase.library.library.Controllers;

import org.springframework.web.bind.annotation.RestController;

import ase.library.library.Entities.BorrowRecord;
import ase.library.library.Services.BorrowService;
import ase.library.library.Util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class BurrowController {

    private final BorrowService borrowService;

    @PutMapping("burrow/{id}")
    public ResponseEntity<Void> burrowBook(@PathVariable Long id) {

        var isBorrowed = borrowService.BorrowBook(id, 3);

        return isBorrowed ? Util.ok()
                : ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
    }

    @PutMapping("burrow/return/{burrowId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long burrowId) {

        var isBorrowed = borrowService.ReturnBook(burrowId, 3);

        return isBorrowed ? Util.ok()
                : ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
    }

    @GetMapping("burrows")
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowService.GetAllBorrowRecords();
    }

}
