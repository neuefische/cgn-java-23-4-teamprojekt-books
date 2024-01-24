package de.neuefische.team2.backend.models;

import lombok.With;
import org.springframework.data.annotation.Id;

public record Book(
        @Id
        String id,
        String title,
        String author

/*      String genre,
        String publication_date
*/

       ) {

}
