package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.models.BookDto;
import de.neuefische.team2.backend.service.BookService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto bookDto){
      return bookService.addBook(bookDto);
    }






}
