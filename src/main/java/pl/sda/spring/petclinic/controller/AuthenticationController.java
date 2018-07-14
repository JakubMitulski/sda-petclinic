package pl.sda.spring.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping(value = {"/loginform"})
    public String getLoginPage() {
        return "loginform";
    }
}
