package nick.pack.controller;

import nick.pack.model.Review;

import nick.pack.model.User;
import nick.pack.repository.UserRepository;
import nick.pack.service.CommentService;
import nick.pack.service.RatingService;
import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Collections;
import java.util.List;

@Controller
public class ViewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    public String index(Model model){
        List<Review> reviews = reviewService.findByAll();
        Collections.reverse(reviews);
        model.addAttribute("reviews", reviews);
        model.addAttribute("ratingService", ratingService);
        return "index";
    }

    @GetMapping("/{id}")
    public String review(@PathVariable("id") int id, Model model){
        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        model.addAttribute("ratingService", ratingService);
        model.addAttribute("comments", commentService.findCommentsReview(review));
        return "review";
    }

    @GetMapping("/{name}")
    public String userPage(@PathVariable("name") String name, Model model){
        User user = repository.test(name);
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviewService.findReviewsByUser(user));
        return "index";
    }
}
