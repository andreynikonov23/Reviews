package nick.pack.controller;

import nick.pack.model.Comment;
import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.service.CommentService;
import nick.pack.service.RatingService;
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
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/set-rating")
    @PreAuthorize("hasAuthority('crud')")
    public void setRating(@RequestParam ("id") int id, @RequestBody String requestBody){
        System.out.println("Request ----------- " + id + ":- " + requestBody);

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);
        Review review = reviewService.findById(id);

        int beginIndex = requestBody.indexOf(":") + 2;
        int endIndex = requestBody.lastIndexOf("\"");
        String valueStr = requestBody.substring(beginIndex, endIndex);

        Rating rating = new Rating(Integer.parseInt(valueStr), user, review);
        ratingService.saveAndFlush(rating);
    }
}
