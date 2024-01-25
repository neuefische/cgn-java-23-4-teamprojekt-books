package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.models.BookCreate;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
public class BookService {
    private final BooksRepo booksRepo;
    private final IdService idService;

    public BookService(BooksRepo booksRepo, IdService idService) {
        this.booksRepo = booksRepo;
        this.idService= idService;
    }

    public List<Book> getBooks() {
        return booksRepo.findAll();
    }

    public Book updateBook(Book book) {
        return booksRepo.save(book);
    }
    public Book getById(String id){return booksRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "No book with such id!"));
    }

    public Book deleteBookById(String id) {
        Book bookToDelete = getById(id);
        booksRepo.delete(bookToDelete);
        return bookToDelete;
    }

    public Book addBook(BookCreate bookCreate){

        String id = idService.newId();
        Book book = new Book(id, bookCreate.title(), bookCreate.author(), bookCreate.isbn(), bookCreate.genre(), bookCreate.publicationDate(), bookCreate.imageURL());
        return booksRepo.save(book);
    }


}
