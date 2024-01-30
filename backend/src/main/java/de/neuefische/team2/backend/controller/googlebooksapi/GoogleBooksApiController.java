package de.neuefische.team2.backend.controller.googlebooksapi;

import de.neuefische.team2.backend.exception.NoSuchBookException;
import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import de.neuefische.team2.backend.service.googlebooksapi.GoogleBooksApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/googleBooks")
@RequiredArgsConstructor
public class GoogleBooksApiController {

    private final GoogleBooksApiService googleBooksApiService;

    @GetMapping("/{isbn}")
    public VolumeInfo getBookDescriptionByIsbn(@PathVariable String isbn, @RequestParam String title) throws NoSuchBookException {
        return googleBooksApiService.getBookBlurb(isbn, title);
    }

}
