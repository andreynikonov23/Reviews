package nick.pack.unit;

import nick.pack.model.User;
import nick.pack.repository.UserRepository;
import nick.pack.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    public void loginExistsTestTrue() {
        User user = new User();
        user.setLogin("test");
        Mockito.when(repository.returnUserObjByLogin(user.getLogin())).thenReturn(new User());
        Assertions.assertTrue(service.loginExists(user));
    }
    @Test
    public void loginExistsTestFalse() {
        User user = new User();
        user.setLogin("test");
        Mockito.when(repository.returnUserObjByLogin(user.getLogin())).thenReturn(null);
        Assertions.assertFalse(service.loginExists(user));
    }
    @Test
    public void emailExistsTestTrue() {
        User user = new User();
        user.setEmail("test");
        Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(new User());
        Assertions.assertTrue(service.emailExists(user));
    }
    @Test
    public void emailExistsTestFalse() {
        User user = new User();
        user.setEmail("test");
        Mockito.when(repository.findUserByEmail(user.getEmail())).thenReturn(null);
        Assertions.assertFalse(service.emailExists(user));
    }

}
