package de.neuefische.team2.backend.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void testBookDefaultConstructor() {
        // Given
        String id = "1";
        String title = "Test Title";
        String author = "Test Author";
        String isbn = "Test ISBN";
        String genre = "Test Genre";
        String publicationDate = "Test Publication Date";
        String imageUrl = "Test Image URL";
        boolean isFavourite = true;


        // When
        Book book = new Book(id, title, author, isbn, genre, publicationDate, imageUrl, isFavourite);

        // Then
        assertEquals(id, book.id());
        assertEquals(title, book.title());
        assertEquals(author, book.author());
        assertEquals(isbn, book.isbn());
        assertEquals(genre, book.genre());
        assertEquals(publicationDate, book.publicationDate());
        assertEquals(imageUrl, book.imageUrl());
        assertEquals(isFavourite, book.isFavourite());
    }

    @Test
    void testBookConstructorWithIdAndBookCreate() {
        // Given
        String id = "1";
        String title = "Test Title";
        String author = "Test Author";
        String isbn = "Test ISBN";
        String genre = "Test Genre";
        String publicationDate = "Test Publication Date";
        String imageUrl = "Test Image URL";
        boolean isFavourite = true;

        BookCreate bookCreate = new BookCreate(title, author, isbn, genre, publicationDate, imageUrl, isFavourite);

        // When
        Book result = new Book(id, bookCreate);

        // Then
        assertEquals(id, result.id());
        assertEquals(title, result.title());
        assertEquals(author, result.author());
        assertEquals(isbn, result.isbn());
        assertEquals(genre, result.genre());
        assertEquals(publicationDate, result.publicationDate());
        assertEquals(imageUrl, result.imageUrl());
        assertEquals(isFavourite, result.isFavourite());
    }
}