package dumpyurl.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public class URLModel {

    private int Id;
    private String longURL;
    private String shortURL;
    private String error;
}
