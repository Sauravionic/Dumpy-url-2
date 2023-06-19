package dumpyurl.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "URLModel")
@Data
public class URLModel {

    @org.springframework.data.annotation.Id
    private String id;
    private String shorturl;
    private String longurl;

}
