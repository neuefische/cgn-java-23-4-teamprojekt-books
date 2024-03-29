package de.neuefische.team2.backend.controller.user;

import de.neuefische.team2.backend.models.user.User;
import de.neuefische.team2.backend.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUser(principal);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
