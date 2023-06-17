package dumpyurl.Service;

import dumpyurl.Model.URLModel;
import dumpyurl.Repository.URLRepository;
import io.seruco.encoding.base62.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class URLService {

    @Autowired
    URLRepository urlRepository;

    //Take long url and convert it into short url
    private String makeShortUrl(String fullURL) {
        Base62 base62 = Base62.createInstance();
        final byte[] encoded = base62.encode(fullURL.getBytes());
        String shortURL = new String(encoded); // eg:- "73XpUgyMwkGr29M"
        return shortURL;
    }

    //Check if shortURL is not in database already
    private boolean shortUrlExists(String shortURL) {
        URLModel urlModel = urlRepository.findByShortURL(shortURL);
        return urlModel == null;
    }

    //save the shorturl in database
    private URLModel saveInDB(URLModel urlModel) {
        return urlRepository.save(urlModel);
    }
}
