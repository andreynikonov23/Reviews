package nick.pack.unit;

import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.repository.RatingRepository;
import nick.pack.service.RatingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RatingServiceTest {
    @Autowired
    private RatingService service;
    @MockBean
    private RatingRepository repository;


    @Test
    public void saveRatingWhenUserRatingIsSet() {
        User testUser = new User();
        testUser.setId(1);
        Review testReview = new Review();
        testReview.setId(1);
        Rating rating = new Rating(7, testUser, testReview);
        Rating ratingDB = new Rating(4, 3, testUser, testReview);
        Mockito.when(repository.findRatingByUserAndReview(testUser, testReview)).thenReturn(ratingDB);
        service.saveAndFlush(rating);
        Assertions.assertEquals(7, ratingDB.getRating());
        Mockito.verify(repository).saveAndFlush(ratingDB);
    }
    @Test
    public void saveRatingWhenUserRatingIsNotSet() {
        User testUser = new User();
        testUser.setId(1);
        Review testReview = new Review();
        testReview.setId(1);
        Rating rating = new Rating(7, testUser, testReview);
        Mockito.when(repository.findRatingByUserAndReview(testUser, testReview)).thenReturn(null);
        service.saveAndFlush(rating);
        Mockito.verify(repository).saveAndFlush(rating);
    }
    @Test
    public void isSetRatingTestTrue() {
        User testUser = new User();
        testUser.setId(1);
        Review testReview = new Review();
        testReview.setId(1);
        Mockito.when(repository.findRatingByUserAndReview(testUser, testReview)).thenReturn(new Rating(7, testUser,testReview));
        Assertions.assertEquals(7, service.isSetRating(testUser, testReview));
    }
    @Test
    public void isSetRatingTestFalse() {
        User testUser = new User();
        testUser.setId(1);
        Review testReview = new Review();
        testReview.setId(1);
        Mockito.when(repository.findRatingByUserAndReview(testUser, testReview)).thenReturn(null);
        Assertions.assertEquals(0, service.isSetRating(testUser, testReview));
    }
    @Test
    public void getOverallRatingTestTrue() {
        Review testReview = new Review();
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(new Rating(7, new User(), testReview));
        ratingList.add(new Rating(3, new User(), testReview));
        ratingList.add(new Rating(10, new User(), testReview));
        ratingList.add(new Rating(5, new User(), testReview));
        Mockito.when(repository.findRatingByReview(testReview)).thenReturn(ratingList);
        Assertions.assertEquals(6.25, service.getOverallRating(testReview));
    }
    @Test
    public void getOverallRatingTestFalse() {
        Review testReview = new Review();
        Mockito.when(repository.findRatingByReview(testReview)).thenReturn(new ArrayList<>());
        Assertions.assertEquals(0d, service.getOverallRating(testReview));
    }
}
