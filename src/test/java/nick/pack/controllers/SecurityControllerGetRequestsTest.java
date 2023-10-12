package nick.pack.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

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
    public void registration(){

    }
}
