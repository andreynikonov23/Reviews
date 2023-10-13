package nick.pack.controllers;

import nick.pack.model.Review;
import nick.pack.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/init-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ViewControllerPostRequestsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("user")
    public void saveReviewTest() throws Exception{
        ReviewService reviewService = Mockito.mock(ReviewService.class);

        Review review = new Review()
    }
}
