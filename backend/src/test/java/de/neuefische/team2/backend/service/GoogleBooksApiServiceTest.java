package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.exception.NoSuchBookException;

import de.neuefische.team2.backend.models.googlebooksapi.ImageLinks;
import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import de.neuefische.team2.backend.service.googlebooksapi.GoogleBooksApiService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleBooksApiServiceTest {

    private static MockWebServer mockWebServer;

    private static GoogleBooksApiService googleBooksApiService;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        googleBooksApiService = new GoogleBooksApiService(mockWebServer.url("/").toString(), "someKey");
    }

    @AfterAll
    public static void cleanup() throws IOException {
        mockWebServer.shutdown();
    }


    @DirtiesContext
    @Test
    void getBookDescriptionTest_WhenIsbnAndTitle_ThenReturnDescription() throws NoSuchBookException {
        //GIVEN
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
        VolumeInfo actual = googleBooksApiService.getVolumeInfo("9780451524935", Optional.of("1984"));

        //THEN
        assertEquals(new VolumeInfo("1984", List.of("George Orwell"), "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                        new ImageLinks("http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api")),
                actual);
    }

    @DirtiesContext
    @Test
    void getBookDescriptionTest_WhenIsbn_ThenReturnDescription() throws NoSuchBookException {
        //GIVEN
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
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
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
                                            "maturityRating": "NOT_MATURE",
                                            "allowAnonLogging": false,
                                            "contentVersion": "preview-1.0.0",
                                            "panelizationSummary": {
                                                "containsEpubBubbles": false,
                                                "containsImageBubbles": false
                                            },
                                            "imageLinks": {
                                                    "thumbnail": "http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api"
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
        VolumeInfo actual = googleBooksApiService.getVolumeInfo("9780451524935", Optional.empty());

        //THEN
        assertEquals(new VolumeInfo("1984", List.of("George Orwell"), "Written more than 70 years ago, 1984 was George Orwell’s chilling prophecy about the future. And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever... • Nominated as one of America’s best-loved novels by PBS’s The Great American Read • “The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.” Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching... A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
                        new ImageLinks("http://books.google.com/books/content?id=SYu-4-oO3h8C&printsec=frontcover&img=1&zoom=1&source=gbs_api")),
                actual);
    }

    @Test
    void getBookDescriptionTest_WhenIsbnAndTitleNotExist_ThenThrowException() {
        //GIVEN
        String notExistingIsbn = "123";
        Optional<String> notExistingTitle = Optional.of("Bla");

        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                            "kind": "books#volumes",
                            "totalItems": 2
                        }"""));

        //WHEN & THEN
        Exception exception = assertThrows(NoSuchBookException.class, () -> googleBooksApiService.getVolumeInfo(notExistingIsbn, notExistingTitle));

        String expectedMessage = "Could not find any book with ISBN " + notExistingIsbn;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
