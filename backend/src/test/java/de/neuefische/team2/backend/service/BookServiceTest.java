package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    BooksRepo booksRepo = Mockito.mock(BooksRepo.class);

    @Test
    public void getBooksTest_returnListOfAllBook(){
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
    public void deleteToDoTest(){
        //GIVEN
        Mockito.when(booksRepo.findBookById(Mockito.any())).thenReturn(
                new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"));

        BookService toDoService = new BookService(booksRepo);

        //WHEN
        Book actual = toDoService.deleteBookById("1");

        //THEN
        assertEquals(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"), actual);

        Mockito.verify(booksRepo, Mockito.times(1)).findBookById(Mockito.any());
        Mockito.verify(booksRepo, Mockito.times(1)).delete(Mockito.any());
        Mockito.verifyNoMoreInteractions(booksRepo);
    }

}
