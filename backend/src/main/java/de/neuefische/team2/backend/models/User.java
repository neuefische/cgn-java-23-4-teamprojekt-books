package de.neuefische.team2.backend.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public record User(
        @Id
        String id,
        int githubId,
        String name,
        List<String> books,
        List<String> favouriteBooks
) {
}
