package de.neuefische.team2.backend.controller.books;

import de.neuefische.team2.backend.models.books.Book;
import de.neuefische.team2.backend.models.books.BookCreate;
import de.neuefische.team2.backend.service.books.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Book deleteBookById(@PathVariable String id) {
        return bookService.deleteBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody BookCreate bookCreate) {
        return bookService.addBook(bookCreate);
    }


}
