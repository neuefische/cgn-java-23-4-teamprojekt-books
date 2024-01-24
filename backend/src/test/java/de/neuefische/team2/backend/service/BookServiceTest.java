package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    BooksRepo booksRepo = Mockito.mock(BooksRepo.class);

    @Test
    public void getBooksTest_returnListOfAllBook() {
        //GIVEN
        Mockito.when(booksRepo.findAll()).thenReturn(List.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"),
                new Book("2", "Harry Potter und die Kammer des Schreckens", "J.K. Rowling")
        ));

        BookService bookService = new BookService(booksRepo);

        //WHEN
        List<Book> actual = bookService.getBooks();

        //THEN
        assertEquals(List.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"),
                new Book("2", "Harry Potter und die Kammer des Schreckens", "J.K. Rowling")
        ), actual);

        Mockito.verify(booksRepo, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    public void updateBookTest_returnBookWithUpdatedAuthor_whenBookWithUpdatedAuthorSent() {
        //GIVEN
        Book udpatedBook = new Book("1", "Harry Potter und der Stein der Weisen", "JayKay Rowlings");
        Mockito.when(booksRepo.save(Mockito.any())).thenReturn(udpatedBook);

        BookService bookService = new BookService(booksRepo);


        //WHEN
        Book actual = bookService.updateBook(udpatedBook);

        //THEN
        assertEquals(udpatedBook, actual);

        Mockito.verify(booksRepo, Mockito.times(1)).save(udpatedBook);
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    public void getBookByIdTest_returnBookWithId() {
        //GIVEN
        String expectedId = "1";
        Mockito.when(booksRepo.findById(expectedId)).thenReturn(Optional.of(
                new Book("1", "Title", "Author")
        ));
        BookService bookService = new BookService(booksRepo);
        //WHEN
        Book foundBook = bookService.getById(expectedId);
        //THEN
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(expectedId, foundBook.id());
    }
}
