package de.neuefische.team2.backend.service.user;

import de.neuefische.team2.backend.models.user.User;
import de.neuefische.team2.backend.repos.user.UsersRepo;
import de.neuefische.team2.backend.service.IdService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepo usersRepo;
    private final IdService idService;

    public User getUser(OAuth2User user) {
        if (user == null) {
            return null;
        }

        Integer githubId = user.getAttribute("id");

        if (githubId == null) {
            return null;
        }

        String name = user.getAttribute("login");
        boolean isReturningUser = usersRepo.existsUserByGithubId(githubId);

        if (!isReturningUser) {
            return usersRepo.save(new User(idService.newId(), githubId, name, new ArrayList<>(), new ArrayList<>()));
        }

        return usersRepo.findUserByGithubId(githubId);
    }

    public User updateUser(User user) {
        return usersRepo.save(user);
    }
}
