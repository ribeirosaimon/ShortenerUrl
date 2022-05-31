package br.com.saimon.ShortenerUrl.repository;

import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface URLRepository extends MongoRepository<ShorterURL, String> {
    Optional<ShorterURL> findByHash(String hash);
}
