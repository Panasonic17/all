package app.controllers;

import app.entity.Word;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnglishWords {

    @RequestMapping("/english/getWord")
    public Word index() {
        return new Word("1","2");
    }

}
