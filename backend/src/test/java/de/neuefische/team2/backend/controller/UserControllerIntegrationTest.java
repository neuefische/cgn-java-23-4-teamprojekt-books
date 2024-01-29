package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.user.User;
import de.neuefische.team2.backend.repos.user.UsersRepo;
import de.neuefische.team2.backend.service.IdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersRepo usersRepo;

    @MockBean
    private IdService idService;

    @Test
    void updateUser_ShouldReturnUpdatedUser() throws Exception {
        // Given
        User updatedUser = new User("123", 123, "Paul", new ArrayList<>(), new ArrayList<>());
        when(usersRepo.save(updatedUser)).thenReturn(updatedUser);

        // When and Then
        mockMvc.perform(put("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""" 
                        {
                            "id": "123",
                            "githubId": 123,
                            "name": "Paul",
                            "books": [],
                            "favouriteBooks": []
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("Paul"));

        // Verify interactions with mocked dependencies
        verify(usersRepo).save(updatedUser);
        verifyNoMoreInteractions(usersRepo, idService);
    }
}
