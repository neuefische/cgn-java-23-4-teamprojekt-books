package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.repos.BooksRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BooksRepo booksRepo;

    @DirtiesContext
    @Test
    void getBooksTest_shouldReturnListWithOneObject_whenOneObjectWasSavedInRepository() throws Exception {
        //GIVEN
        booksRepo.save(new Book("1", "Harry Potter und der Stein der Weisen", "J.K. Rowling"));

        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                             "id": "1",
                             "title": "Harry Potter und der Stein der Weisen",
                             "author": "J.K. Rowling"
                         }]
                        """))
                .andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    void getBookByIdTest_shouldReturnObjectWithTheId() throws Exception {
        //GIVEN
        Book book = booksRepo.save(new Book("1", "Title", "Author"));
        String id = book.id();
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", id))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "id":"1",
                        "title":"Title",
                        "author":"Author"
                        }
                        """))
                .andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);

    }
    @Test
    void getBookByNoExistingIdTest_shouldReturnNoObject() throws Exception {
        //GIVEN
        Book book = booksRepo.save(new Book("1", "Title", "Author"));
        String nonExistingId = "nonExistingId";
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", nonExistingId))
                //THEN
                .andExpect(status().isNotFound())
                .andReturn();
        Assertions.assertEquals(mvcResult.getResponse().getStatus(), 404);

    }
}
