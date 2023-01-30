package nick.pack.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {
    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        return "registration";
    }
}