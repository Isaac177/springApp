package kz.narxoz.springapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class controller {
    @GetMapping("/hello")
    public  String hello (){
        return "hello !!";
    }
    @GetMapping("/bye")
    public String bye (){
        return "Bye !!!";
    }
}
