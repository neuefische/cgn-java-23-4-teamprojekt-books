package de.neuefische.team2.backend.repos.user;

import de.neuefische.team2.backend.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends MongoRepository<User, String> {
    User findUserByGithubId(int id);
    Boolean existsUserByGithubId(int id);
}
