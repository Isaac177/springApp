package kz.narxoz.springapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           @RequestParam(name="surname", required=false, defaultValue="World") String surname, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "greeting";
    }

}