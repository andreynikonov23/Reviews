package nick.pack.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/api")
public class ApiViewController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('crud')")
    public String test(Model model){
        Object login = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("user", login);
        return "test";
    }
}