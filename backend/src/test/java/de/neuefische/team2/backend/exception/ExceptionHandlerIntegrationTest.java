package de.neuefische.team2.backend.exception;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlerIntegrationTest {

    private static MockWebServer mockWebServer;

    @Autowired
    public MockMvc mockMvc;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

    }

    @DynamicPropertySource
    public static void configureUrl(DynamicPropertyRegistry registry) {
        registry.add("app.googleBooks.api.url", () -> mockWebServer.url("/").toString());
    }

    @DirtiesContext
    @Test
    void globalExceptionHandlerTest_BookNotFoundException() throws Exception {
        //Given
        String notExistingIsbn = "123";
        String notExistingTitle = "Bla";

        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                            "kind": "books#volumes",
                                "totalItems": 0
                        }"""));


        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/googleBooks/{isbn}?title={title}", notExistingIsbn, notExistingTitle))

                //THEN
                .andExpect(status().is(404))
                .andReturn();

        assertEquals(404, mvcResult.getResponse().getStatus());
        }

}
