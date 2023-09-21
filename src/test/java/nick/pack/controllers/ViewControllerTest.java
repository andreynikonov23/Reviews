package nick.pack.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import javax.xml.xpath.XPathExpressionException;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@Sql(value = {"/sql/init-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ViewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void opening() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("REVIEWS")))
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[1]/div[@data-id='8']").exists())
                .andExpect(xpath("/html/body/aside/div/div[3]/div[8]/div/div").exists());
    }

    //USER Security Test
    @Test
    @WithUserDetails("user")
    public void openingWithAdmin() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/header/header/div[1]/div/div[3]/div/div[1]/div[1]/p").string("UserNickname"))
                .andExpect(xpath("/html/body/aside/div/div[3]/div[8]/div/div/div/div/div[1]/p[@data-id='1']").exists())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[6]/div/div/div[3]/p[@data-id='3']").exists())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[5]/div/div/div[3]/p[@data-id='4']").exists())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[1]/div/div/div[1]/p[@data-id='8']").exists())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[7]/div/div/div[1]/div/a[@data-value='pencil']").exists())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[7]/div/div/div[1]/div/span[@data-value='trash']").exists())
                .andExpect(xpath("/html/body/aside/div/div[3]/div[8]/div/div/div/div/div[3]/div/span[@data-value='trash']").doesNotExist());
    }
    @Test
    public void searchTest() throws Exception {
        this.mockMvc.perform(get("/search?value=TestReview8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/div/div/div").nodeCount(1));
    }
}
