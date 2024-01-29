package de.neuefische.team2.backend.models.books;

import lombok.With;
import org.springframework.data.annotation.Id;

@With
public record Book(
        @Id
        String id,
        String title,
        String author,
        String isbn,
        String genre,
        String publicationDate,
        String imageUrl
) {
}
