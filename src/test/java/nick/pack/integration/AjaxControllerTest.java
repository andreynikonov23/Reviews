package nick.pack.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import nick.pack.model.Comment;
import nick.pack.service.ReviewService;
import nick.pack.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/init-data.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AjaxControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @Test
    @WithUserDetails("user")
    public void setRatingWithAuthorityUser() throws Exception {
        String json = "{\"rating\":\"6\"}";
        this.mockMvc.perform(post("/set-rating").param("id", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[7]/div/div/div[3]/p").string("6"));
    }
    @Test
    public void setRatingWithGuest() throws Exception {
        String json = "{\"rating\":\"6\"}";
        this.mockMvc.perform(post("/set-rating").param("id", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("user")
    public void sendCommentWithAuthorityUser() throws Exception {
        String json = "{\"answer\":\"\",\"comment\":\"testComment\",\"date\":\"2023-10-19 13:35\"}";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Comment comment = new Comment();
        comment.setId(6);
        comment.setComment("testComment");
        comment.setDate(LocalDateTime.parse("2023-10-19 13:35", formatter));
        comment.setUser(userService.findUserByLogin("user"));
        comment.setReview(reviewService.findById(1));
        ObjectMapper objectMapper = new ObjectMapper();
        this.mockMvc.perform(post("/send-comment").param("review", "1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(objectMapper)));
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(xpath("//*[@id=\"6\"]").exists())
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[@id='6']/div/div[2]/span").string("testComment"));
    }
    @Test
    public void sendCommentWithGuest() throws Exception {
        String json = "{\"answer\":\"\",\"comment\":\"testComment\",\"date\":\"2023-10-19 13:35\"}";
        this.mockMvc.perform(post("/send-comment").param("review", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
