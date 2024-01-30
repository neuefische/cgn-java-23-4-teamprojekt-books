package de.neuefische.team2.backend.service.googlebooksapi;

import de.neuefische.team2.backend.exception.NoSuchBookException;
import de.neuefische.team2.backend.models.googlebooksapi.GoogleBooksResponse;
import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GoogleBooksApiService {
    private final RestClient restClient;


    public GoogleBooksApiService(@Value("${app.googleBooks.api.url}") String url,
                                 @Value("${app.googleBooks.api.key}") String apiKey) {

        restClient = RestClient.builder()
                .baseUrl(url)
                .defaultHeader("key", apiKey)
                .build();
    }

    public VolumeInfo getBookBlurb(String isbn, String title) throws NoSuchBookException {
            GoogleBooksResponse response = restClient.get()
                    .uri("/volumes?q=isbn:" + isbn + "+intitle:" + title + "&langRestrict=en&printType=books&projection=lite")
                    .retrieve()
                    .body(GoogleBooksResponse.class);

            if(response == null || response.items() == null){
                throw new NoSuchBookException("Could not find any book with ISBN " + isbn);
            }
            return response.items().getFirst().volumeInfo();

    }
}
