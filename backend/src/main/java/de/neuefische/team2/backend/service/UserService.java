package de.neuefische.team2.backend.service;

import de.neuefische.team2.backend.models.User;
import de.neuefische.team2.backend.repos.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;
    private final IdService idService;

    public User getUser(OAuth2User user) {
        if (user == null) {
            return null;
        }

        int githubId = user.getAttribute("id");
        String name = user.getAttribute("login");
        boolean isReturningUser = usersRepo.existsUserByGithubId(githubId);

        if (!isReturningUser) {
            usersRepo.save(new User(idService.newId(), githubId, name));
        }

        return usersRepo.findUserByGithubId(user.getAttribute("id"));
    }
}
