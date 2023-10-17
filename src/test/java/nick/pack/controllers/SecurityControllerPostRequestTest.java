package nick.pack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nick.pack.mail.MailSenderService;
import nick.pack.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
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
    @MockBean
    private MailSenderService mailSender;

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

        MockHttpServletRequestBuilder multipart = multipart("/signup")
                .file("file", "test".getBytes())
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
}
