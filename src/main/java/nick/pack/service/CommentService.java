package nick.pack.service;

import nick.pack.model.Comment;
import nick.pack.repository.CommentRepository;
import nick.pack.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentService implements DAO<Comment, Integer> {
    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAndFlush(Comment comment) {
        repository.saveAndFlush(comment);
    }

    @Override
    public void delete(Comment comment) {
        repository.delete(comment);
    }

    @Override
    public List<Comment> findByAll() {
        return repository.findAll();
    }

    @Override
    public Comment findById(Integer integer) {
        return repository.findCommentById(integer);
    }
}
