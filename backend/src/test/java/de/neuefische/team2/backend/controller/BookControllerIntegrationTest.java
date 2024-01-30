package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.books.Book;
import de.neuefische.team2.backend.repos.books.BooksRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BooksRepo booksRepo;

    @DirtiesContext
    @Test
    void getBooksTest_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepository() throws Exception {
        //GIVEN
        booksRepo.save(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www"));

        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                             "id": "1",
                             "title": "Harry Potter und der Stein der Weisen",
                             "author": "J.K. Rowling",
                             "isbn": "123",
                             "genre": "Fantasy",
                             "publicationDate": "someday",
                             "imageUrl": "www"
                         }]
                        """))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @DirtiesContext
    @Test
    void updateBooksTest_shouldReturnBookWithUpdatedAuthor_whenBookWithUpdatedAuthorSent() throws Exception {
        //GIVEN
        Book book = new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www");

        //WHEN
        booksRepo.save(book);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                                                 
                                {
                                     "id":"1",
                                     "title":"Harry Potter und der Stein der Weisen",
                                     "author":"JayKay Rowling",
                                     "isbn": "123",
                                     "genre": "Fantasy",
                                     "publicationDate": "someday",
                                     "imageUrl": "www"
                                }
                                      
                                 """))

                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                             "id": "1",
                             "title": "Harry Potter und der Stein der Weisen",
                             "author": "JayKay Rowling",
                             "isbn": "123",
                             "genre": "Fantasy",
                             "publicationDate": "someday",
                             "imageUrl": "www"
                         }
                        """))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @DirtiesContext
    @Test
    void getBookByIdTest_shouldReturnObjectWithTheId() throws Exception {
        //GIVEN
        Book book = booksRepo.save(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www"));
        String id = book.id();
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", id))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                             "title": "Harry Potter und der Stein der Weisen",
                             "author": "J.K. Rowling",
                             "isbn": "123",
                             "genre": "Fantasy",
                             "publicationDate": "someday",
                             "imageUrl": "www"
                        }
                        """))
                .andReturn();
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

    }

    @DirtiesContext
    @Test
    void getBookByNoExistingIdTest_shouldReturnNoObject() throws Exception {
        //GIVEN
        String nonExistingId = "nonExistingId";
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", nonExistingId))
                //THEN
                .andExpect(status().isNotFound())
                .andReturn();
        Assertions.assertEquals(404, mvcResult.getResponse().getStatus());

    }

    @DirtiesContext
    @Test
    void deleteBook_shouldReturnBook_whenThisObjectWasDeletedFromRepository() throws Exception {
        //GIVEN
        booksRepo.save(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling",
                "123", "Fantasy", "someday", "www"));

        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))

                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "id": "1",
                            "title": "Harry Potter und der Stein der Weisen",
                            "author": "J.K. Rowling",
                            "isbn": "123",
                            "genre": "Fantasy",
                            "publicationDate": "someday",
                            "imageUrl": "www"
                         }
                        """))
                .andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void addBookTest_shouldReturnOneObject_whenObjectWasSavedInRepository() throws Exception {

        // GIVEN
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                 {
                                    "title": "Harry Potter und der Stein der Weisen",
                                    "author": "J.K. Rowling",
                                    "isbn": "123",
                                    "genre": "Fantasy",
                                    "publicationDate": "someday",
                                    "imageUrl": "www"
                                 }
                                """)
                )
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""

                          {
                                "title": "Harry Potter und der Stein der Weisen",
                                "author": "J.K. Rowling",
                                "isbn": "123",
                                "genre": "Fantasy",
                                "publicationDate": "someday",
                                "imageUrl": "www"
                          }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty());

    }

}
