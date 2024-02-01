package de.neuefische.team2.backend.service.googlebooksapi;

import de.neuefische.team2.backend.exception.NoSuchBookException;
import de.neuefische.team2.backend.models.googlebooksapi.GoogleBooksResponse;
import de.neuefische.team2.backend.models.googlebooksapi.ImageLinks;
import de.neuefische.team2.backend.models.googlebooksapi.Item;
import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

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

    public VolumeInfo getVolumeInfo(String isbn, Optional<String> title) throws NoSuchBookException {

        GoogleBooksResponse response;

        if(title.isPresent()){
            response = restClient.get()
                    .uri("/volumes?q=isbn:" + isbn + "+intitle:" + title.get() + "&langRestrict=en&printType=books&projection=lite")
                    .retrieve()
                    .body(GoogleBooksResponse.class);
        } else {
            response = restClient.get()
                    .uri("/volumes?q=isbn:" + isbn +  "&langRestrict=en&printType=books&projection=lite")
                    .retrieve()
                    .body(GoogleBooksResponse.class);
        }

        if (response == null || response.items() == null) {
            throw new NoSuchBookException("Could not find any book with ISBN " + isbn);
        }

        Optional<Item> item = response.items().stream().max(Comparator.comparingInt(a ->
                a.volumeInfo().description().length()));

        return item.isPresent() ? item.get().volumeInfo() : new VolumeInfo("", new ArrayList<>(), "", new ImageLinks(""));

    }
}
