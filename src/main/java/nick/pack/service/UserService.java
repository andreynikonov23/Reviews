package nick.pack.service;

import nick.pack.model.User;
import nick.pack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> selectAll(){
        return repository.findAll();
    }
    public User findById(int id){
        return repository.findById(id);
    }
    public void saveAndFlush(User user){
        repository.saveAndFlush(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
