package nick.pack.controllers;

import nick.pack.model.*;
import nick.pack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
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
    private CountryService countryService;

                //Servlets
            //Get mappings
    //Main page
    @GetMapping("/")
    public String index(Model model){
        setAuthorizedUserAsModel(model);

        List<Review> reviews = reviewService.findByAll();
        Collections.reverse(reviews);

        model.addAttribute("reviews", reviews);
        model.addAttribute("ratingService", ratingService);

        return "index";
    }

    //Review page
    @GetMapping("/{id}")
    public String review(@PathVariable("id") int id, Model model){
        setAuthorizedUserAsModel(model);

        Review review = reviewService.findById(id);
        model.addAttribute("review", review);
        model.addAttribute("ratingService", ratingService);
        model.addAttribute("comments", commentService.findCommentsReview(review));

        return "review";
    }

    //User page
    @GetMapping("/user")
    public String user(@RequestParam(name = "id") int id, Model model){
        setAuthorizedUserAsModel(model);

        User user = userService.findUserById(id);
        boolean admin = user.getRole().getRoleName().equals(RoleEnum.ADMIN);

        model.addAttribute("user", user);
        model.addAttribute("isAdmin", admin);
        model.addAttribute("isActive", user.isActive());
        model.addAttribute("reviews", reviewService.findReviewsByUser(user));
        model.addAttribute("ratingService", ratingService);

        return "user";
    }

    //Search result page
    @GetMapping("/search")
    public String search(@RequestParam(name="value", required = false) String value, Model model){
        setAuthorizedUserAsModel(model);

        if (value.isEmpty()){
            return "redirect:/";
        }
        List<Review> result = reviewService.findByAll().stream().filter(x -> x.getName().toLowerCase().contains(value.toLowerCase()) || x.getFilmName().toLowerCase().contains(value.toLowerCase())).toList();
        model.addAttribute("reviews", result);
        model.addAttribute("ratingService", ratingService);
        model.addAttribute("value", value);

        return "search";
    }

    //Page for creating the review
    @GetMapping("/add-review")
    @PreAuthorize("hasAuthority('crud')")
    public String addReviewForm(Model model){
        setAuthorizedUserAsModel(model);

        Review review = new Review();
        List<Country> countries = countryService.findByAll();

        model.addAttribute("review", review);
        model.addAttribute("countries", countries);

        return "addReview";
    }

    //Page for changing the review
    @GetMapping("/edit-review")
    @PreAuthorize("hasAuthority('crud')")
    public String editReview(@RequestParam("id") int id, Model model){
        User user = setAuthorizedUserAsModel(model);

        Review review = reviewService.findById(id);
        if (review.getUser().equals(user)){
            List<Country> countries = countryService.findByAll();

            model.addAttribute("review", review);
            model.addAttribute("countries", countries);
            model.addAttribute("edit", true);

            return "editReview";
        }

        return "redirect:/";
    }

    //Page with all users for admin
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('viewuser')")
    public String users(Model model){
        setAuthorizedUserAsModel(model);
        List<User> users = new ArrayList<>(userService.selectAll());
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isAdmin()){
                users.remove(i);
            }
        }
        model.addAttribute("users", users);

        return "usersList";
    }

            //Post mappings
    //Creating review
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('crud')")
    public String addReview(@ModelAttribute("review") Review review, Model model){
        User user = setAuthorizedUserAsModel(model);
        review.setUser(user);
        reviewService.saveAndFlush(review);

        return "redirect:/user?id=" + user.getId();

    }
    //Changing review
    @PostMapping("/edit")
    @PreAuthorize("hasAuthority('crud')")
    public String saveReview(@ModelAttribute("review") Review review, Model model){
        User user = setAuthorizedUserAsModel(model);

        if (review.getUser() == null){
            return "redirect:/";
        }

        if (!(review.getUser().equals(user))){
            return "redirect:/";
        }

        Review testReview = reviewService.findById(review.getId());
        if (!(testReview.getUser().equals(review.getUser()))){
            return "redirect:/";
        }

        reviewService.saveAndFlush(review);

        return "redirect:/user?id=" + user.getId();

    }
    //Deleting review
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('crud')")
    public String deleteReview(@ModelAttribute("id") Integer id, Model model){
        User user = setAuthorizedUserAsModel(model);

        Review review = reviewService.findById(id);
        if (!(review.getUser().equals(user))){
            return "redirect:/error";
        }
        reviewService.delete(review);

        return "redirect:/user?id=" + user.getId();
    }


            //Common methods
    //Returns an authorized user object and adds it to the Model
    public User setAuthorizedUserAsModel(Model model){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByLogin(login);

        if (user != null){
            model.addAttribute("authorityUserIsAdmin", user.isAdmin());
        }
        System.out.println(user);
        model.addAttribute("authorityUser", user);
        return user;
    }
}
