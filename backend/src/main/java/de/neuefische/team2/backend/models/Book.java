package de.neuefische.team2.backend.models;

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
        String imageUrl,
        boolean isFavourite
) {

    public Book(String id, BookCreate bookCreate) {
        this(id, bookCreate.title(), bookCreate.author(), bookCreate.isbn(), bookCreate.genre(), bookCreate.publicationDate(), bookCreate.imageUrl(), bookCreate.isFavourite());
    }
}
