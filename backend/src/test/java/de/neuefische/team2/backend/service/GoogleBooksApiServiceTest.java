package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.exception.NoSuchBookException;
import de.neuefische.team2.backend.models.googlebooksapi.VolumeInfo;
import de.neuefische.team2.backend.service.googlebooksapi.GoogleBooksApiService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleBooksApiServiceTest {

    GoogleBooksApiService googleBooksApiService = Mockito.mock(GoogleBooksApiService.class);

    @Test
    public void getBookDescriptionTest_WhenIsbnAndTitle_ThenReturnDescription() throws NoSuchBookException {
        //GIVEN
        Mockito.when(googleBooksApiService.getBookBlurb(Mockito.any(), Mockito.any()))
                .thenReturn(new VolumeInfo("1984", List.of("George Orwell"), "Summary"));

        //WHEN
        VolumeInfo actual = googleBooksApiService.getBookBlurb("123", "Blabla");

        //THEN
        assertEquals(new VolumeInfo("1984", List.of("George Orwell"), "Summary"), actual);
        Mockito.verify(googleBooksApiService, Mockito.times(1)).getBookBlurb(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(googleBooksApiService);

    }

    @Test
    public void getBookDescriptionTest_WhenIsbnAndTitleNotExist_ThenThrowException() throws NoSuchBookException {
        //GIVEN
        String notExistingIsbn = "123";
        String notExistingTitle = "Bla";

        Mockito.when(googleBooksApiService.getBookBlurb(notExistingIsbn, notExistingTitle))
                .thenThrow(new NoSuchBookException("No Book found"));

        //WHEN & THEN
        Exception exception = assertThrows(NoSuchBookException.class, () -> googleBooksApiService.getBookBlurb(notExistingIsbn, notExistingTitle));

        String expectedMessage = "No Book found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        Mockito.verify(googleBooksApiService, Mockito.times(1)).getBookBlurb(Mockito.any(), Mockito.any());
        Mockito.verifyNoMoreInteractions(googleBooksApiService);

    }
}
