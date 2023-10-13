package nick.pack.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class SecurityControllerGetRequestsTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void loginTest() throws Exception{
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(content().string(containsString("Вход")));
    }
    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(formLogin().user("admin").password("1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(formLogin().user("admin").password("1234444444"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
    @Test
    public void errorLoginTest() throws Exception{
        this.mockMvc.perform(get("/login?error"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/div/div/div[3]/div").string("Неверный логин или пароль, либо пользователь заблокирован"));
    }
    @Test
    public void registrationTest() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Регистрация")));
    }
    @Test
    public void recoverTest() throws Exception {
        this.mockMvc.perform(get("/recover"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Восстановление доступа")));
    }
    @Test
    @WithUserDetails("user")
    public void deleteProfileTest() throws Exception {
        this.mockMvc.perform(get("/delete-profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Удаление профиля")));
    }
    @Test
    @WithUserDetails("user")
    public void editProfileTest() throws Exception {
        this.mockMvc.perform(get("/edit-profile"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id=\"nick\"][@value='UserNickname']").exists());
    }
}
