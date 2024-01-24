package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {
    private final BooksRepo booksRepo;

    public BookService(BooksRepo booksRepo){
        this.booksRepo = booksRepo;
    }
    public List<Book> getBooks(){
        return booksRepo.findAll();
    }
    public Book getById(String id){return booksRepo.findById(id).orElseThrow(()-> new NoSuchElementException("No book with such id!"));
    }
}
