package nick.pack.service;

import nick.pack.model.User;
import nick.pack.repository.UserRepository;
import nick.pack.security.SecurityUser;
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
        return repository.findUserById(id);
    }
    public void saveAndFlush(User user){
        repository.saveAndFlush(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
    public User findUserById(int id){
        return repository.findUserById(id);
    }
    public boolean userExists(User user){
        return repository.findByLogin(user.getLogin()).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
