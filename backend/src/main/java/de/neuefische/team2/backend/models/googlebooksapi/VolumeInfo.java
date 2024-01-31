package de.neuefische.team2.backend.models.googlebooksapi;

import java.util.List;

public record VolumeInfo(
        String title,
        List<String> authors,
        String description,
        ImageLinks imageLinks
) {
}
