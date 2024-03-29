package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.books.Book;
import de.neuefische.team2.backend.models.books.BookCreate;
import de.neuefische.team2.backend.repos.books.BooksRepo;
import de.neuefische.team2.backend.service.books.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    BooksRepo booksRepo = Mockito.mock(BooksRepo.class);
    IdService idService = Mockito.mock(IdService.class);


    @Test
    void getBooksTest_returnListOfAllBook() {
        //GIVEN
        Mockito.when(booksRepo.findAll()).thenReturn(List.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                        "123", "Fantasy", "someday", "www", "blurb"),
                new Book("2", "Harry Potter und die Kammer des Schreckens", "J.K. Rowling",
                        "456", "Fantasy", "someotherday", "www2", "blurb2")));

        BookService bookService = new BookService(booksRepo, idService);

        //WHEN
        List<Book> actual = bookService.getBooks();

        //THEN
        assertEquals(List.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                        "123", "Fantasy", "someday", "www", "blurb"),
                new Book("2", "Harry Potter und die Kammer des Schreckens", "J.K. Rowling",
                        "456", "Fantasy", "someotherday", "www2", "blurb2")
        ), actual);

        Mockito.verify(booksRepo, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    void updateBookTest_returnBookWithUpdatedAuthor_whenBookWithUpdatedAuthorSent() {
        //GIVEN
        Book udpatedBook = new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www", "blurb");
        Mockito.when(booksRepo.save(Mockito.any())).thenReturn(udpatedBook);

        BookService bookService = new BookService(booksRepo, idService);


        //WHEN
        Book actual = bookService.updateBook(udpatedBook);

        //THEN
        assertEquals(udpatedBook, actual);

        Mockito.verify(booksRepo, Mockito.times(1)).save(udpatedBook);
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    void getBookByIdTest_returnBookWithId() {
        //GIVEN
        String expectedId = "1";
        Mockito.when(booksRepo.findById(expectedId)).thenReturn(Optional.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                        "123", "Fantasy", "someday", "www", "blurb")));
        BookService bookService = new BookService(booksRepo, idService);

        //WHEN
        Book foundBook = bookService.getById(expectedId);
        //THEN
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(expectedId, foundBook.id());
        Mockito.verify(booksRepo, Mockito.times(1)).findById(Mockito.any());
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    void deleteToDoTest() {
        //GIVEN
        Mockito.when(booksRepo.findById(Mockito.any())).thenReturn(
                Optional.of(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                        "123", "Fantasy", "someday", "www", "blurb")));

        BookService toDoService = new BookService(booksRepo, idService);

        //WHEN
        Book actual = toDoService.deleteBookById("1");

        //THEN
        assertEquals(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www", "blurb"), actual);

        Mockito.verify(booksRepo, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(booksRepo, Mockito.times(1)).delete(Mockito.any());
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    void addBookTest_returnBook() {
        // GIVEN
        BookCreate bookCreate = new BookCreate("Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www", "blurb");
        Book book = bookCreate.withId("1");

        Mockito.when(booksRepo.save(Mockito.any())).thenReturn(book);
        Mockito.when(idService.newId()).thenReturn("1");
        BookService bookService = new BookService(booksRepo, idService);

        // WHEN
        Book actual = bookService.addBook(bookCreate);

        // THEN
        Mockito.verify(booksRepo).save(book);
        Mockito.verify(idService).newId();

        Book expected = new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www", "blurb");
        assertEquals(expected, actual);
    }

}
