package dumpyurl.Repository;

import dumpyurl.Model.URLModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface URLRepository extends MongoRepository<URLModel, Integer> {

    public URLModel findByShortURL(String shortURL);
}
