package ase.library.library.Dtos.Book;

import ase.library.library.Entities.Enums.BookCat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookDto {

    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    private BookCat category;
    private String fileUrl;
    private long fileSizeMb;
    private String shelfLocation;
    private int copiesAvailable;
    private boolean ebook;

}
