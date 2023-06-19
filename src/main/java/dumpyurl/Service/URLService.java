package dumpyurl.Service;

import dumpyurl.Model.URLModel;
import dumpyurl.Repository.URLRepository;
import io.seruco.encoding.base62.Base62;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class URLService {

    @Autowired
    URLRepository urlRepository;

    //Take long url and convert it into short url
    public String makeShortUrl(String fullURL) {
        Base62 base62 = Base62.createInstance();
        final byte[] encoded = base62.encode(fullURL.getBytes());
        String shortURL = new String(encoded); // eg:- "73XpUgyMwkGr29M"
        return shortURL;
    }


    //Check if shortURL is not in database already
    public List<URLModel> shortUrlExists(String shorturl) {
        List<URLModel> shorturlList = urlRepository.findByShorturl(shorturl);
        return shorturlList;
    }

    //save the shorturl in database
    public URLModel saveInDB(URLModel urlModel) {
        return urlRepository.save(urlModel);
    }
}
