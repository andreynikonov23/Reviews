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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

//Контроллер для обработки ajax-запросов
@RestController
public class AjaxController {
    private static final Logger logger = Logger.getLogger(AjaxController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;


    //Fetch-запрос для рейтинга
    @PostMapping("/set-rating")
    @PreAuthorize("hasAuthority('crud')")
    public void setRating(@RequestParam ("id") int id, @RequestBody String requestBody){
        try {
            logger.debug("/set-rating [post-mapping] with requestParam: idReview=" + id + "& requestBody: " + requestBody);

            User user = getAuthorityUser();

            Review review = reviewService.findById(id);

            int beginIndex = requestBody.indexOf(":") + 2;
            int endIndex = requestBody.lastIndexOf("\"");
            String valueStr = requestBody.substring(beginIndex, endIndex);

            Rating rating = new Rating(Integer.parseInt(valueStr), user, review);
            logger.debug("save rating: " + rating + " in database");
            ratingService.saveAndFlush(rating);
        } catch (Exception e){
            logger.error("/set-rating with requestParam: idReview=" + id + "& requestBody: " + requestBody);
            e.printStackTrace();
        }

    }


    //Fetch-запрос для комментариев
    @PostMapping("/send-comment")
    @PreAuthorize("hasAuthority('crud')")
    public Comment sendComment(@RequestParam ("review") int id, @RequestBody CommentDTO commentDTO){
        logger.debug("/send-comment [post-mapping] with requestParam: idReview=" + id + "& requestBody: " + commentDTO);

        LocalDateTime date = commentDTO.getDate();
        Review review = reviewService.findById(id);
        User user = getAuthorityUser();
        logger.debug("via " + user);

        Comment comment = new Comment(commentDTO.getComment(), date, user, review);

        Comment answer;
        if (commentDTO.getAnswer() != 0){
            answer = commentService.findById(commentDTO.getAnswer());
            comment.setAnswer(answer);
        }

        commentService.saveAndFlush(comment);

        return comment;
    }


    //Возвращает объект авторизованного пользователя
    public User getAuthorityUser(){
        logger.info("get authority user from database...");
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByLogin(login);
    }
}
