package nick.pack.service;

import nick.pack.model.*;
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
    public User findUserByLogin(String login){
        return repository.returnUserObjByLogin(login);
    }
    public User findUserByEmail(String email){
        return repository.findUserByEmail(email);
    }


    public boolean loginExists(User user){
        User userDB = repository.returnUserObjByLogin(user.getLogin());
        return userDB != null;
    }
    public boolean emailExists(User user){
        User userSql = repository.findUserByEmail(user.getEmail());
        return userSql != null;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByLogin(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return SecurityUser.fromUser(user);
    }
}
