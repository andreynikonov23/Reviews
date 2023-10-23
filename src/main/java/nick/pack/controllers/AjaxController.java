package nick.pack.controllers;

import nick.pack.model.Comment;
import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.service.CommentService;
import nick.pack.service.RatingService;
import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import nick.pack.utils.CommentDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//Controller for processing ajax requests
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


    //Fetch-request for rating
    @PostMapping("/set-rating")
    @PreAuthorize("hasAuthority('crud')")
    public void setRating(@RequestParam ("id") int id, @RequestBody String requestBody){
        User user = getAuthorityUser();

        Review review = reviewService.findById(id);

        int beginIndex = requestBody.indexOf(":") + 2;
        int endIndex = requestBody.lastIndexOf("\"");
        String valueStr = requestBody.substring(beginIndex, endIndex);

        Rating rating = new Rating(Integer.parseInt(valueStr), user, review);
        ratingService.saveAndFlush(rating);
    }


    //Fetch-request for comment
    @PostMapping("/send-comment")
    @PreAuthorize("hasAuthority('crud')")
    public Comment sendComment(@RequestParam ("review") int id, @RequestBody CommentDTO commentDTO){
        LocalDateTime date = commentDTO.getDate();
        Review review = reviewService.findById(id);
        User user = getAuthorityUser();

        Comment comment = new Comment(commentDTO.getComment(), date, user, review);

        Comment answer;
        if (commentDTO.getAnswer() != 0){
            answer = commentService.findById(commentDTO.getAnswer());
            comment.setAnswer(answer);
        }

        commentService.saveAndFlush(comment);

        return comment;
    }


    //Return object authorization user
    public User getAuthorityUser(){
        return userService.findUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
