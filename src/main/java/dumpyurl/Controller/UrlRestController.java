package dumpyurl.Controller;

import dumpyurl.Model.URLModel;
import dumpyurl.Service.URLService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UrlRestController {

    @Autowired
    private URLService urlService;

    @PostMapping("/makeUrl")
    public ResponseEntity<String> saveURL(@RequestBody String fullURL) {

        //Make sure fullURL is a valid Url
        UrlValidator urlValidator = new UrlValidator(new String[]{"http","https"});

        //if fullUrl is valid
        if(urlValidator.isValid(fullURL)) {
            //Encode
            String shortURL = urlService.makeShortUrl(fullURL);

            //Make Modelclass
            URLModel urlModel = new URLModel();
            urlModel.setId(UUID.randomUUID().toString());
            urlModel.setShorturl(shortURL);
            urlModel.setLongurl(fullURL);

            //Check if shortURL already exists
            List<URLModel> exists = urlService.shortUrlExists(shortURL);
            System.out.println(exists.size());

            if(exists.size() != 0) {
                return new ResponseEntity<>("Already exists", HttpStatus.FOUND);
            }
            else {
                //save in Database
                urlService.saveInDB(urlModel);
                return new ResponseEntity<>("Success", HttpStatus.OK);
            }
        }
        //fullURL not valid
        return new ResponseEntity<>("Please Enter valid URL", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<URLModel> getFullUrl(@PathVariable String shortUrl) {
        //Check if shortURL already exists
        List<URLModel> exists = urlService.shortUrlExists(shortUrl);
        System.out.println(exists.size());

        // if shortUrl exists
        if(exists.size() != 0) {
            URLModel urlModel = exists.get(0);
            //return the model
            return new ResponseEntity<>(urlModel, HttpStatus.OK);
        }
        //if shortURL does not exists
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
