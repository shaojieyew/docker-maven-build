package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class App {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(@RequestParam(name = "name")  String name){
        return String.format("Hello %s", name);
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}