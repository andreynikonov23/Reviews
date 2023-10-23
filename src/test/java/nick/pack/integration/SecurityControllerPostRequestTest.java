package nick.pack.integration;

import nick.pack.mail.MailSenderService;
import nick.pack.model.*;
import nick.pack.service.RoleService;
import nick.pack.service.StatusService;
import nick.pack.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/init-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SecurityControllerPostRequestTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private UserService userService;
    @MockBean
    private MailSenderService mailSender;
    @MockBean
    private HashMap<String, User> unconfirmedUsers;
    @SpyBean
    private ArrayList<User> confirmedUsers;

    @Test
    public void registrationEmptyFields() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/signup")
                .file("file", null)
                .param("nick", "")
                .param("email", "")
                .param("login", "")
                .param("password", "")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[1]/div[2]/div[2]").string("*Пустое поле Имя"))
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[1]/div[2]/div[4]").string("*Пустое поле Email"))
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[2]/div[2]/div").string("*Пустое поле - Логин"))
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[2]/div[4]/div").string("*Пустое поле - Пароль"));
    }
    @Test
    public void registrationWithExistingEmail() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/signup")
                .file("file", null)
                .param("nick", "test")
                .param("email", "gogag51389@sesxe.com")
                .param("login", "test")
                .param("password", "1")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[4]/div").string("*Пользователь с данным email уже существует"));
    }
    @Test
    public void registrationWithExistingLogin() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/signup")
                .file("file", null)
                .param("nick", "test")
                .param("email", "testmail@mail.com")
                .param("login", "user")
                .param("password", "1")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/form/div[3]/div").string("*Пользователь с данным логином уже существует"));
    }
    @Test
    public void registration() throws Exception {
        File file = new File("classpath:/src/test/resources/img-test/icon.jpg");
        MockMultipartFile multipartFile;
        try(FileInputStream input = new FileInputStream(file.getAbsolutePath().replaceAll("classpath:", ""))){
            multipartFile = new MockMultipartFile("file", "test.jpg", MediaType.IMAGE_JPEG_VALUE, input.readAllBytes());
        }

        MockHttpServletRequestBuilder multipart = multipart("/signup")
                .file(multipartFile)
                .param("nick", "test")
                .param("email", "testmail@mail.com")
                .param("login", "test")
                .param("password", "1")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Введите код активации, который был выслан вам на почту")));
    }
    @Test
    public void activationAccountTest() throws Exception {
        User user = new User("test", "$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi", "testNickname", "test@email.com", null);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        Mockito.when(unconfirmedUsers.get("test-0000")).thenReturn(user);
        String data = "code=0000&login=test";

        this.mockMvc.perform(post("/activate")
                            .contentType("application/x-www-form-urlencoded")
                            .content(data))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        this.mockMvc.perform(formLogin().user("test").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    public void activationAccountTestWithNotValidCode() throws Exception {
        User user = new User("test", "$2a$12$K91NDBeibhwvvRl.T1gP3OoxQyPsCZii/ladJoeeCWK9AwqnLIMxi", "testNickname", "test@email.com", null);
        user.setRole(roleService.setUserRole());
        user.setStatus(statusService.setActiveStatus());
        Mockito.when(unconfirmedUsers.get("test-0000")).thenReturn(user);
        String data = "code=1111&username=test";

        this.mockMvc.perform(post("/activate")
                        .contentType("application/x-www-form-urlencoded")
                        .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/form/div").string("Код активации неверный"));
    }
    @Test
    public void mailSendTestValidEmail() throws Exception {
        String email = "email=andreynikonov13@yandex.ru";
        this.mockMvc.perform(post("/mail-send")
                        .contentType("application/x-www-form-urlencoded")
                        .content(email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Введите код активации, который был выслан вам на почту")));
    }
    @Test
    public void mailSendTestNotValidEmail() throws Exception {
        String email = "email=notexistingemail@email.com";
        this.mockMvc.perform(post("/mail-send")
                            .contentType("application/x-www-form-urlencoded")
                            .content(email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/form/div").string("Пользователь с данным email не зарегистрирован"));
    }
    @Test
    public void recoverAccess() throws Exception {
        String data = "code=0000&login=user";
        Mockito.when(unconfirmedUsers.get("user-0000")).thenReturn(userService.findUserById(2));
        this.mockMvc.perform(post("/recover-request")
                            .contentType("application/x-www-form-urlencoded")
                            .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/p").string("Введите новый пароль"));
    }
    @Test
    public void recoverAccessWithNotValidCode() throws Exception {
        String data = "code=1111&login=user";
        Mockito.when(unconfirmedUsers.get("user-0000")).thenReturn(userService.findUserById(2));
        this.mockMvc.perform(post("/recover-request")
                        .contentType("application/x-www-form-urlencoded")
                        .content(data))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/form/div").string("Код активации неверный"));
    }
    @Test
    public void editPasswordTest() throws Exception {
        User user = userService.findUserById(2);
        confirmedUsers.add(user);
        String data = "user=2&password=2";
        this.mockMvc.perform(post("/edit-password")
                            .contentType("application/x-www-form-urlencoded")
                            .content(data))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        this.mockMvc.perform(formLogin().user("user").password("2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        confirmedUsers.clear();
    }
    @Test
    public void editPasswordWithUnconfirmedUser() throws Exception {
        confirmedUsers.clear();
        String data = "user=2&password=2";
        this.mockMvc.perform(post("/edit-password")
                        .contentType("application/x-www-form-urlencoded")
                        .content(data))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }
    @Test
    @WithUserDetails("user")
    public void editProfileTest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/edit-user")
                .file("file", null)
                .param("user", "2")
                .param("nick", "test")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        this.mockMvc.perform(get("/user").param("id", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[3]").string("test"));
    }
    @Test
    @WithUserDetails("admin")
    public void editProfileWithAlienUser() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/edit-user")
                .file("file", null)
                .param("user", "2")
                .param("nick", "test")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }
    @Test
    public void editProfileWithGuest() throws Exception {
        MockHttpServletRequestBuilder multipart = multipart("/edit-user")
                .file("file", null)
                .param("user", "2")
                .param("nick", "test")
                .with(csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("user")
    public void deleteProfileTest() throws Exception {
        this.mockMvc.perform(post("/delete-user")
                            .contentType("application/x-www-form-urlencoded")
                            .content("password=1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        this.mockMvc.perform(formLogin().user("user").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
    @Test
    @WithUserDetails("user")
    public void deleteProfileInvalidPassword() throws Exception {
        this.mockMvc.perform(post("/delete-user")
                        .contentType("application/x-www-form-urlencoded")
                        .content("password=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/form/div[1]").string("Не верный пароль"));
    }
    @Test
    public void deleteProfileWithGuest() throws Exception {
        this.mockMvc.perform(post("/delete-user")
                        .contentType("application/x-www-form-urlencoded")
                        .content("password=2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
