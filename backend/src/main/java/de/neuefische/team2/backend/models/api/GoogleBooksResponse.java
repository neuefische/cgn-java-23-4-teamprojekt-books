package de.neuefische.team2.backend.models.api;

import java.util.List;

public record GoogleBooksResponse(
        int totalItems,
        List<Item> items
) {
}
