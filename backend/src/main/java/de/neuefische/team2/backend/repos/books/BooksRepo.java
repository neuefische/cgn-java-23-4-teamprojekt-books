package de.neuefische.team2.backend.repos.books;

import de.neuefische.team2.backend.models.books.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepo extends MongoRepository<Book, String> {
}
