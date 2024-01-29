package de.neuefische.team2.backend.service.user;

import de.neuefische.team2.backend.models.user.User;
import de.neuefische.team2.backend.repos.user.UsersRepo;
import de.neuefische.team2.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private IdService idService;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserShouldReturnNullWhenUserIsNull() {
        // When
        User result = userService.getUser(null);

        // Then
        assertNull(result);
        verifyNoInteractions(usersRepo);
    }

    @Test
    void getUserShouldSaveNewUserWhenUserNotInDatabase() {
        // Given
        OAuth2User user = mock(OAuth2User.class);
        int id = 123456;
        String name = "Vanessa";
        when(user.getAttribute("id")).thenReturn(id);
        when(user.getAttribute("name")).thenReturn(name);
        when(usersRepo.existsUserByGithubId(id)).thenReturn(false);
        when(idService.newId()).thenReturn("generatedId");

        User generatedUser = new User("generatedId", id, name, new ArrayList<>(), new ArrayList<>());
        when(usersRepo.save(any(User.class))).thenReturn(generatedUser);

        // When
        User result = userService.getUser(user);

        // Then
        assertNotNull(result);
        assertEquals("generatedId", result.id());
        assertEquals(id, result.githubId());
        verify(usersRepo, times(1)).existsUserByGithubId(id);
        verify(idService, times(1)).newId();
        verify(usersRepo, times(1)).save(any(User.class));
    }

    @Test
    void getUserShouldReturnExistingUserWhenUserInDatabase() {
        // Given
        OAuth2User user = mock(OAuth2User.class);
        int id = 123456;
        String name = "Vanessa";
        when(user.getAttribute("id")).thenReturn(id);
        when(usersRepo.existsUserByGithubId(id)).thenReturn(true);
        when(user.getAttribute("name")).thenReturn(name);
        User existingUser = new User("existingId", id, name, new ArrayList<>(), new ArrayList<>());
        when(usersRepo.findUserByGithubId(id)).thenReturn(existingUser);

        // When
        User result = userService.getUser(user);

        // Then
        assertNotNull(result);
        assertEquals(existingUser, result);
        verify(usersRepo, times(1)).existsUserByGithubId(id);
        verify(usersRepo, times(1)).findUserByGithubId(id);
        verifyNoMoreInteractions(idService, usersRepo);
    }
}
