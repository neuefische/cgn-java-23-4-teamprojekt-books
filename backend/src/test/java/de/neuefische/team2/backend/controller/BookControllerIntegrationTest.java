package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.Book;
import de.neuefische.team2.backend.repos.BooksRepo;
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
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
    void addBookTest_shouldReturnOneObject_whenObjectWasSavedInRepository() throws Exception {

        // GIVEN
        // WHEN
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         {
                           "title": "Harry Potter und der Stein der Weisen",
                           "author": "J.K. Rowling"
                         }
                        """)
        )
        // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""

                  {
                     "title": "Harry Potter und der Stein der Weisen",
                      "author": "J.K. Rowling"
                  }
                """))
                .andExpect(jsonPath("$.id").isNotEmpty());

    }

}
