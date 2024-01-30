package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import de.neuefische.team2.backend.service.googlebooksapi.GoogleBooksApiService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleBooksApiServiceTest {

    GoogleBooksApiService googleBooksApiService = Mockito.mock(GoogleBooksApiService.class);

    //GIVEN
    @Test
    public void getBookDescriptionTest_WhenIsbnAndTitle_ThenReturnDescription() {
        Mockito.when(googleBooksApiService.getBookBlurb(Mockito.any(), Mockito.any()))
                .thenReturn(new VolumeInfo("1984", List.of("George Orwell"), "Summary"));

        //WHEN
        VolumeInfo actual = googleBooksApiService.getBookBlurb("123", "Blabla");

        //THEN
        assertEquals(new VolumeInfo("1984", List.of("George Orwell"), "Summary"), actual);
        Mockito.verify(googleBooksApiService, Mockito.times(1)).getBookBlurb(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(googleBooksApiService);

    }

}
