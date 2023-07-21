package nick.pack.controller;

import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AjaxController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/get-user-json")
    public User getAuthUserInfo(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);
        return user;
    }

    @PostMapping("/get-review-json")
    public Review getReview(@RequestBody String reviewId){
        int beginIndex = reviewId.indexOf(":") + 2;
        int endIndex = reviewId.lastIndexOf("\"");
        StringBuilder builder = new StringBuilder(reviewId);
        return reviewService.findById(Integer.parseInt(builder.substring(beginIndex, endIndex)));
    }

    @PostMapping("/set-rating")
    @PreAuthorize("hasAuthority('crud')")
    public void setRating(@RequestBody Rating rating){
        System.out.println(rating);
    }
}
