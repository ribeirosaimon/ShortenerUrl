package br.com.saimon.ShortenerUrl.repository;

import br.com.saimon.ShortenerUrl.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
