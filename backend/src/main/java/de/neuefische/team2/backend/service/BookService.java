package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.models.BookDto;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BooksRepo booksRepo;
    private final IdService idService;

    public BookService(BooksRepo booksRepo, IdService idService){
        this.booksRepo = booksRepo;
        this.idService= idService;
    }

    public List<Book> getBooks(){
        return booksRepo.findAll();
    }

    public Book addBook(BookDto bookDto){

        String id = idService.newId();
        Book book = new Book(id, bookDto.title(), bookDto.author());
        return booksRepo.save(book);
    }


}
