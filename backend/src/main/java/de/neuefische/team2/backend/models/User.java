package de.neuefische.team2.backend.models;

import org.springframework.data.annotation.Id;

public record User(
        @Id
        String id,
        int githubId,
        String name
) {
}
