package ase.library.library.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ase.library.library.Entities.BorrowRecord;
import java.util.List;

public interface BorrowRepo extends JpaRepository<BorrowRecord, Long> {

    BorrowRecord findByBookIdOrderByIdDesc(long bookId);

    boolean existsByBookIdAndUserIdAndReturnDateIsNull(long bookId, long userId);

    BorrowRecord findByBookIdAndUserIdAndReturnDateIsNull(long bookId, long userId);

    List<BorrowRecord> findAllByOrderByIdDesc();

}
