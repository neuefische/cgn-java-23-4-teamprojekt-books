package de.neuefische.team2.backend.models;

import lombok.With;

public record BookDto(
        String title,
        String author
/*        String genre,
        String publication_date*/
        )
{
}
