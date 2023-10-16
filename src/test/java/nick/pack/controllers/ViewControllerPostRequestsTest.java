package nick.pack.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/init-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ViewControllerPostRequestsTest {
    @Autowired
    private MockMvc mockMvc;
    private final String CREATE_BODY = "trailerUrl=&poster=&filmName=testFilm9&year=2023&director=testDirector9&country=8&cast=testCast9&name=TestReview9&text=texttexttexttext";
    private final String EDIT_BODY = "trailerUrl=&id=1&poster=&filmName=testName1&year=2023&director=testDirector1&country=1&cast=testCast1&name=TestEditReview&text=text+text+text+text+text";

    @Test
    @WithUserDetails("user")
    public void createReviewTestWithAuthority() throws Exception{
        this.mockMvc.perform(post("/add")
                        .content(CREATE_BODY)
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user?id=2"));
        this.mockMvc.perform(get("/user").param("id", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(4));
    }
    @Test
    @WithUserDetails("admin")
    public void editReviewTest() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .content(EDIT_BODY + "&user=1")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user?id=1"));
        this.mockMvc.perform(get("/user").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[3]/div/div/div/div/a").string("TestEditReview"));
    }
    @Test
    @WithUserDetails ("user")
    public void editReviewTestWithAlienUser() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .content(EDIT_BODY + "&user=1")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    @WithUserDetails ("user")
    public void editReviewTestWithoutAuthor() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .content(EDIT_BODY)
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    @WithUserDetails ("user")
    public void editReviewTestWithAlienAuthor() throws Exception {
        this.mockMvc.perform(post("/edit")
                        .content(EDIT_BODY + "&user=2")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    public void addReviewTestWithGuest() throws Exception {
        this.mockMvc.perform(post("/add")
                        .content(CREATE_BODY)
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    public void editReviewTestWithGuest() throws Exception{
        this.mockMvc.perform(post("/edit")
                        .content(EDIT_BODY + "&user=1")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("admin")
    public void deleteTest() throws Exception{
        this.mockMvc.perform(post("/delete")
                        .content("id=7")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user?id=1"));
        this.mockMvc.perform(get("/user").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(2));
    }
    @Test
    @WithUserDetails("user")
    public void deleteTestWithAlienUser() throws Exception{
        this.mockMvc.perform(post("/delete")
                        .content("id=7")
                        .contentType("application/x-www-form-urlencoded"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error"));
    }
}
