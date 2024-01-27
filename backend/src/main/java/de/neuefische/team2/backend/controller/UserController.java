package de.neuefische.team2.backend.controller;

import de.neuefische.team2.backend.models.User;
import de.neuefische.team2.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public User getLoggedInUser(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUser(principal);
    }
}
