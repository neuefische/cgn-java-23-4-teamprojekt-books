package de.neuefische.team2.backend.service.api;

import de.neuefische.team2.backend.models.api.GoogleBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

    public String getBookDescription(String isbn, String title){
        GoogleBooksResponse response = restClient.get()
                .uri("/volumes?q=isbn:" + isbn + "+intitle:" + title + "&langRestrict=en&printType=books&projection=lite")
                .retrieve()
                .body(GoogleBooksResponse.class);

        assert response != null;
        response.items().getFirst().volumeInfo().description();

        return null;
    }
}
