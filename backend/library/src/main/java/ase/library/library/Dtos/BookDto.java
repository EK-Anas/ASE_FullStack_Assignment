package ase.library.library.Dtos;

import ase.library.library.Entities.Enums.BookCat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private BookCat category;

}
