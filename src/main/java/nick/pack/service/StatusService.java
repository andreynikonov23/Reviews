package nick.pack.service;

import nick.pack.model.Status;
import nick.pack.repository.StatusRepository;
import nick.pack.service.dao.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements Find<Status, Integer> {
    private final StatusRepository repository;

    @Autowired
    public StatusService(StatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Status> findByAll() {
        return repository.findAll();
    }

    @Override
    public Status findById(Integer integer) {
        return repository.findStatusById(integer);
    }
}
