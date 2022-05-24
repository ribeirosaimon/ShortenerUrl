package br.com.saimon.ShortenerUrl.repository;

import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface URLRespository extends MongoRepository<ShorterURL, String> {
}
