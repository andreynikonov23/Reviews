package nick.pack.service;

import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.repository.RatingRepository;
import nick.pack.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements DAO<Rating, Integer> {
    private final RatingRepository repository;

    @Autowired
    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }


    @Override
    public void saveAndFlush(Rating rating) {
        Rating ratingDB = repository.findRatingByUserAndReview(rating.getUser(), rating.getReview());
        if (ratingDB != null){
            ratingDB.setRating(rating.getRating());
            repository.saveAndFlush(ratingDB);
        } else
            repository.saveAndFlush(rating);
    }

    @Override
    public void delete(Rating rating) {
        repository.delete(rating);
    }

    @Override
    public List<Rating> findByAll() {
        return repository.findAll();
    }

    @Override
    public Rating findById(Integer integer) {
        return repository.findRatingById(integer);
    }

    public Rating findRatingByUserAndReview(User user, Review review){
        return repository.findRatingByUserAndReview(user, review);
    }

    public List<Rating> findRatingsReview(Review review){
        return repository.findRatingByReview(review);
    }

    public List<Rating> findRatingsUser(User user){
        return repository.findRatingByUser(user);
    }

    public int isSetRating(User authorityUser, Review review){
        Rating rating = findRatingByUserAndReview(authorityUser, review);
        if (rating != null){
            return rating.getRating();
        }
        return 0;
    }

    public double getOverallRating(Review review){
        List<Rating> ratings = repository.findRatingByReview(review);
        if (ratings.size() > 0){
            return ratings.stream().mapToDouble(Rating::getRating).sum() / ratings.size();
        }
        return 0;
    }
}
