package de.neuefische.team2.backend.models;

public record BookCreate(
        String title,
        String author,
        String isbn,
        String genre,
        String publicationDate,
        String imageUrl
) {
}
