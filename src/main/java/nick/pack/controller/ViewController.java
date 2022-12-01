package nick.pack.controller;

import nick.pack.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String test(Model model){
        model.addAttribute("reviews", reviewService.findByAll());
        return "index";
    }
}
