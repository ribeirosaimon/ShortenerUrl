package br.com.saimon.ShortenerUrl.repository;

import br.com.saimon.ShortenerUrl.domain.ShorterURL;
import br.com.saimon.ShortenerUrl.utils.Util;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@DisplayName("Repository Test")
class RepositoryURLTest {

    ShorterURL shorterURL = Util.newUrl();
    
    private final String collectionNameTest = "testCollection";

    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(collectionNameTest);
        mongoTemplate.save(shorterURL, collectionNameTest);
    }

    @AfterEach
    public void setUpAffer() {
        mongoTemplate.dropCollection(collectionNameTest);
    }

    @Test
    @DisplayName("load URL in Repository")
    public void load() throws Exception {
        ShorterURL url = mongoTemplate.findById(shorterURL.getId(), shorterURL.getClass(), collectionNameTest);
        Assertions.assertTrue(url.getId().equals(shorterURL.getId()));
    }

    @Test
    @DisplayName("save URL in Repository")
    public void save() throws Exception {
        ShorterURL newUrl = new ShorterURL();
        newUrl = shorterURL;
        newUrl.setId(new ObjectId().toString());

        ShorterURL savedUrl = mongoTemplate.save(newUrl, collectionNameTest);
        long countCollection = mongoTemplate.count(new Query(), collectionNameTest);
        Assertions.assertTrue(countCollection == 2);
    }

    @Test
    @DisplayName("Delete URL in Repository")
    public void delete() {
        DeleteResult remove = mongoTemplate.remove(shorterURL, collectionNameTest);
        Optional<? extends ShorterURL> nullUrl = Optional.ofNullable(mongoTemplate.findById(shorterURL.getId(), shorterURL.getClass(), collectionNameTest));
        long countCollection = mongoTemplate.count(new Query(), collectionNameTest);

        Assertions.assertTrue(nullUrl.isEmpty());
        Assertions.assertTrue(countCollection == 0);
        Assertions.assertTrue(mongoTemplate.collectionExists(collectionNameTest));
    }


}
