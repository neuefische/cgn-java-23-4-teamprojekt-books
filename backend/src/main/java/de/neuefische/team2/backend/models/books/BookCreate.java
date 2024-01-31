package de.neuefische.team2.backend.models.books;

public record BookCreate(
        String title,
        String author,
        String isbn,
        String genre,
        String publicationDate,
        String imageUrl,
        String blurb
) {

    public Book withId(String id) {
        return new Book(id, title(), author(), isbn(), genre(), publicationDate(), imageUrl(), blurb());
    }
}
