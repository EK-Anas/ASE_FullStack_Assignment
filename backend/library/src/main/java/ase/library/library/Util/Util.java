package ase.library.library.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    public static ResponseEntity<Void> ok() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }
}
