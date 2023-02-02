package nick.pack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class SecurityController {
    @Value("${upload.path}")
    String iconFolder;
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
        String fileName = UUID.randomUUID().toString() + " " + file.getOriginalFilename();
        try {
            file.transferTo(new File(iconFolder + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/login";
    }
}