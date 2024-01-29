package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.api.VolumeInfo;
import de.neuefische.team2.backend.service.api.GoogleBooksApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/googleBooks")
@RequiredArgsConstructor
public class GoogleBooksApiController {

    private final GoogleBooksApiService googleBooksApiService;

    @GetMapping("/{isbn}")
    public VolumeInfo getBookDescriptionByIsbn(@PathVariable String isbn, @RequestParam String title){
        return googleBooksApiService.getBookDescription(isbn, title);
    }

}
