package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.models.BookDto;
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
    IdService idService = Mockito.mock(IdService.class);


    @Test
    public void getBooksTest_returnListOfAllBook() {
        //GIVEN
        Mockito.when(booksRepo.findAll()).thenReturn(List.of(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"),
                new Book("2", "Harry Potter und die Kammer des Schreckens", "J.K. Rowling")
        ));

        BookService bookService = new BookService(booksRepo, idService);

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

        BookService bookService = new BookService(booksRepo,idService);


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
        BookService bookService = new BookService(booksRepo,idService);
        //WHEN
        Book foundBook = bookService.getById(expectedId);
        //THEN
        Assertions.assertNotNull(foundBook);
        Assertions.assertEquals(expectedId, foundBook.id());
    }
    @Test
    public void deleteToDoTest(){
        //GIVEN
        Mockito.when(booksRepo.findById(Mockito.any())).thenReturn(
                Optional.of(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling")));

        BookService toDoService = new BookService(booksRepo,idService);

        //WHEN
        Book actual = toDoService.deleteBookById("1");

        //THEN
        assertEquals(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"), actual);

        Mockito.verify(booksRepo, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(booksRepo, Mockito.times(1)).delete(Mockito.any());
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

    @Test
    public void addBookTest_returnBook(){

        BookDto bookDto = new BookDto("Harry Potter und der Stein der Weisen", "J.K. Rowling");
        Book book = new Book("test-id","Harry Potter und der Stein der Weisen", "J.K. Rowling");


        // GIVEN
        Mockito.when(booksRepo.save(book)).thenReturn(book);
        Mockito.when(idService.newId()).thenReturn("test-id");

        BookService bookService = new BookService(booksRepo, idService);


        // WHEN
        Book actual = bookService.addBook(bookDto);

        // THEN
        Mockito.verify(booksRepo).save(book);
        Mockito.verify(idService).newId();

        Book expected = new Book("test-id","Harry Potter und der Stein der Weisen", "J.K. Rowling");
        assertEquals(expected, actual);

    }

}
