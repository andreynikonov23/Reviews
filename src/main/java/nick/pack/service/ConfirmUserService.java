package nick.pack.service;

import nick.pack.model.ConfirmUser;
import nick.pack.repository.ConfirmUserRepository;
import nick.pack.service.dao.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmUserService implements Find<ConfirmUser, Integer> {

    private final ConfirmUserRepository repository;

    @Autowired
    public ConfirmUserService(ConfirmUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ConfirmUser> findByAll() {
        return repository.findAll();
    }

    @Override
    public ConfirmUser findById(Integer integer) {
        return repository.findConfirmUserById(integer);
    }

    public ConfirmUser setConfirmedStatus(){
        return repository.findConfirmUserById(1);
    }

    public ConfirmUser setNotConfirmedStatus(){
        return repository.findConfirmUserById(2);
    }
}
