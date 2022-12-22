package nick.pack.controller;

import nick.pack.model.Review;

import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collections;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String test(Model model){
        List<Review> reviews = reviewService.findByAll();
        Collections.reverse(reviews);
        model.addAttribute("reviews", reviews);
        return "index";
    }
}
