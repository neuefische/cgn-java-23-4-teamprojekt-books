package de.neuefische.team2.backend.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @DirtiesContext
    @Test
    void globalExceptionHandlerTest_BookNotFoundException() throws Exception {
        //GIVEN
        String notExistingIsbn = "123";
        String notExistingTitle = "Bla";

        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/{isbn}?title={title}", notExistingIsbn, notExistingTitle))

                //THEN
                .andExpect(status().is(404))
                .andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
    }

}
