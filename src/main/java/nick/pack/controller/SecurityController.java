package nick.pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SecurityController {
    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("file") MultipartFile file, Model model){

        return "redirect:/login";
    }
}