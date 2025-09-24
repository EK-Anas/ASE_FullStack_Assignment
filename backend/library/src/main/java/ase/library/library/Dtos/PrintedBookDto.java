package ase.library.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PrintedBookDto extends BookDto {

    private String shelfLocation;
    private int copiesAvailable;
}
