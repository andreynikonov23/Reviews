package nick.pack.service;

import nick.pack.model.Role;
import nick.pack.repository.RoleRepository;
import nick.pack.service.dao.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService implements Find<Role, Integer> {
    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findByAll() {
        return repository.findAll();
    }

//    @Override
//    public Role findById(Integer integer) {
//        return repository.findRoleById(integer);
//    }
}
