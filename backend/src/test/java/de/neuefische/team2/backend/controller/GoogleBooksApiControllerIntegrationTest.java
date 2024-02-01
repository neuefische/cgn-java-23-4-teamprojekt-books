package de.neuefische.team2.backend.controller;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoogleBooksApiControllerIntegrationTest {

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

    @AfterAll
    public static void cleanup() throws IOException {
        mockWebServer.shutdown();
    }

    @DirtiesContext
    @Test
    void getBookDescriptionTest_WhenIsbnAndTitle_ThenReturnDescription() throws Exception {
        //Given
        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                            "kind": "books#volumes",
                                "totalItems": 2,
                                "items": [
                                    {
                                        "kind": "books#volume",
                                        "id": "uJOOzwEACAAJ",
                                        "etag": "4zZBx8ENr7w",
                                        "selfLink": "https://www.googleapis.com/books/v1/volumes/uJOOzwEACAAJ",
                                        "volumeInfo": {
                                            "title": "1984",
                                            "authors": [
                                                "George Orwell"
                                            ],
                                            "publisher": "Signet",
                                            "publishedDate": "1950-07-01",
                                            "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                                            "readingModes": {
                                                "text": false,
                                                "image": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                                                },
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "previewLink": "http://books.google.de/books?id=uJOOzwEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&cd=1&source=gbs_api",
                                            "infoLink": "http://books.google.de/books?id=uJOOzwEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&source=gbs_api",
                                            "canonicalVolumeLink": "https://books.google.com/books/about/1984.html?hl=&id=uJOOzwEACAAJ"
                                        },
                                        "saleInfo": {
                                            "country": "DE"
                                        },
                                        "accessInfo": {
                                            "country": "DE",
                                            "epub": {
                                                "isAvailable": false
                                            },
                                            "pdf": {
                                                "isAvailable": false
                                            },
                                            "accessViewStatus": "NONE"
                                        },
                                        "searchInfo": {
                                            "textSnippet": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future."
                                        }
                                    },
                                    {
                                        "kind": "books#volume",
                                        "id": "d3hA0AEACAAJ",
                                        "etag": "oky5qu1+uVM",
                                        "selfLink": "https://www.googleapis.com/books/v1/volumes/d3hA0AEACAAJ",
                                        "volumeInfo": {
                                            "title": "1984",
                                            "authors": [
                                                "George Orwell"
                                            ],
                                            "publisher": "Signet",
                                            "publishedDate": "1950-07-01",
                                            "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                                            "readingModes": {
                                                "text": false,
                                                "image": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                                                },
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "previewLink": "http://books.google.de/books?id=d3hA0AEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&cd=2&source=gbs_api",
                                            "infoLink": "http://books.google.de/books?id=d3hA0AEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&source=gbs_api",
                                            "canonicalVolumeLink": "https://books.google.com/books/about/1984.html?hl=&id=d3hA0AEACAAJ"
                                        },
                                        "saleInfo": {
                                            "country": "DE"
                                        },
                                        "accessInfo": {
                                            "country": "DE",
                                            "epub": {
                                                "isAvailable": false
                                            },
                                            "pdf": {
                                                "isAvailable": false
                                            },
                                            "accessViewStatus": "NONE"
                                        },
                                        "searchInfo": {
                                            "textSnippet": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future."
                                        }
                                    }
                                ]
                        }"""));


        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/googleBooks/9780451524935?title=1984"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "1984",
                        "authors": ["George Orwell"],
                        "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                        "imageLinks":
                            {"thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"}
                        }
                        """))
                .andReturn();


        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @DirtiesContext
    @Test
    void getBookDescriptionTest_WhenIsbn_ThenReturnDescription() throws Exception {
        //Given
        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                            "kind": "books#volumes",
                                "totalItems": 2,
                                "items": [
                                    {
                                        "kind": "books#volume",
                                        "id": "uJOOzwEACAAJ",
                                        "etag": "4zZBx8ENr7w",
                                        "selfLink": "https://www.googleapis.com/books/v1/volumes/uJOOzwEACAAJ",
                                        "volumeInfo": {
                                            "title": "1984",
                                            "authors": [
                                                "George Orwell"
                                            ],
                                            "publisher": "Signet",
                                            "publishedDate": "1950-07-01",
                                            "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                                            "readingModes": {
                                                "text": false,
                                                "image": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                                                },
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "previewLink": "http://books.google.de/books?id=uJOOzwEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&cd=1&source=gbs_api",
                                            "infoLink": "http://books.google.de/books?id=uJOOzwEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&source=gbs_api",
                                            "canonicalVolumeLink": "https://books.google.com/books/about/1984.html?hl=&id=uJOOzwEACAAJ"
                                        },
                                        "saleInfo": {
                                            "country": "DE"
                                        },
                                        "accessInfo": {
                                            "country": "DE",
                                            "epub": {
                                                "isAvailable": false
                                            },
                                            "pdf": {
                                                "isAvailable": false
                                            },
                                            "accessViewStatus": "NONE"
                                        },
                                        "searchInfo": {
                                            "textSnippet": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future."
                                        }
                                    },
                                    {
                                        "kind": "books#volume",
                                        "id": "d3hA0AEACAAJ",
                                        "etag": "oky5qu1+uVM",
                                        "selfLink": "https://www.googleapis.com/books/v1/volumes/d3hA0AEACAAJ",
                                        "volumeInfo": {
                                            "title": "1984",
                                            "authors": [
                                                "George Orwell"
                                            ],
                                            "publisher": "Signet",
                                            "publishedDate": "1950-07-01",
                                            "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                                            "readingModes": {
                                                "text": false,
                                                "image": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                                                },
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "previewLink": "http://books.google.de/books?id=d3hA0AEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&cd=2&source=gbs_api",
                                            "infoLink": "http://books.google.de/books?id=d3hA0AEACAAJ&dq=isbn:9780451524935+intitle:1984&hl=&as_pt=BOOKS&source=gbs_api",
                                            "canonicalVolumeLink": "https://books.google.com/books/about/1984.html?hl=&id=d3hA0AEACAAJ"
                                        },
                                        "saleInfo": {
                                            "country": "DE"
                                        },
                                        "accessInfo": {
                                            "country": "DE",
                                            "epub": {
                                                "isAvailable": false
                                            },
                                            "pdf": {
                                                "isAvailable": false
                                            },
                                            "accessViewStatus": "NONE"
                                        },
                                        "searchInfo": {
                                            "textSnippet": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future."
                                        }
                                    }
                                ]
                        }"""));


        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/googleBooks/9780451524935"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "1984",
                        "authors": ["George Orwell"],
                        "description": "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                        "imageLinks":
                            {"thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"}
                        }
                        """))
                .andReturn();


        assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
