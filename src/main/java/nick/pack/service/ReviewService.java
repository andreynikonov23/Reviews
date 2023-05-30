package nick.pack.service;

import nick.pack.model.Review;
import nick.pack.model.User;
import nick.pack.repository.ReviewRepository;
import nick.pack.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService implements DAO<Review, Integer> {

    private final ReviewRepository repository;

    @Autowired
    private RatingService ratingService;
    @Autowired
    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Review> findByAll() {
        return repository.findAll();
    }

    @Override
    public Review findById(Integer integer) {
        return repository.findReviewById(integer);
    }

    @Override
    public void saveAndFlush(Review review) {
        repository.saveAndFlush(review);
    }

//    @Transactional
//    public void update (Review review){
//        repository.update(review.getId(), review.getName(), review.getTrailerUrl(), review.getPoster(), review.getFilmName(), review.getYear(), review.getDirector(), review.getCast(), review.getText(), review.getUser(), review.getCountry());
//    }
    @Override
    public void delete(Review review) {
        repository.delete(review);
    }

    public List<Review> findReviewsByUser(User user){
        return repository.findReviewsByUser(user);
    }
}
