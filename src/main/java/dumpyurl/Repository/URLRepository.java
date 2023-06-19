package dumpyurl.Repository;

import dumpyurl.Model.URLModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface URLRepository extends MongoRepository<URLModel, String> {
    List<URLModel> findByShorturl(String shorturl);
}
