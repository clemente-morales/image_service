package mx.com.lania.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/coco/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}