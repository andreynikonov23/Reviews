package nick.pack.controller;

import nick.pack.model.Comment;
import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.service.CommentService;
import nick.pack.service.RatingService;
import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import nick.pack.utils.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

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

        User user = getAuthorityUser();

        Review review = reviewService.findById(id);

        int beginIndex = requestBody.indexOf(":") + 2;
        int endIndex = requestBody.lastIndexOf("\"");
        String valueStr = requestBody.substring(beginIndex, endIndex);

        Rating rating = new Rating(Integer.parseInt(valueStr), user, review);
        ratingService.saveAndFlush(rating);
    }
    @PostMapping("/send-comment")
    @PreAuthorize("hasAuthority('crud')")
    public Comment sendComment(@RequestParam ("review") int id, @RequestBody CommentDTO commentDTO){
        System.out.println(commentDTO);
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

    public User getAuthorityUser(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByLogin(login);
    }
}
