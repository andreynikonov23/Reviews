package nick.pack.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@Sql(value = {"/sql/init-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ViewControllerGetRequestsTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void openingWithGuest() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("REVIEWS")))
                .andExpect(xpath("/html/body/header/header/div[1]/div/div[3]/button").string("Вход"))
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[1]/div[@data-id='8']").exists())
                .andExpect(xpath("/html/body/aside/div/div[3]/div[8]/div/div").exists());
    }

    //USER Security Test
    @Test
    @WithUserDetails("user")
    public void openingWithUser() throws Exception {
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
    @WithUserDetails("admin")
    public void openingWithAdmin() throws Exception{
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/aside/div/div[1]/div/div/div[1]/div/div/div[3]/div/span").exists())
                .andExpect(xpath("/html/body/header/header/div[1]/nav/div[5]/a").string("Пользователи"));
    }
    @Test
    public void searchTest() throws Exception {
        this.mockMvc.perform(get("/search?value=TestReview8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/div/div/div").nodeCount(1));
    }
    @Test
    public void alienReviewWithGuest() throws Exception{
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[1]/h1").string("TestReview1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[1]/video/source[@src='https://www.film.ru/sites/default/files/trailers/1615784/Taxi-Driver-Trailer-rus.mp4']").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span").string("testName1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[1]/a").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[2]/span").string("2023"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[3]/span").string("testDirector1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[4]/span").string("США"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[5]/span").string("testCast1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/img[@src='https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg']").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[3]/div").string("text text text text text"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[2]").string("6.0"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/span").string("testComment1"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/span[1]/a").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/span").doesNotExist());
    }
    @Test
    @WithUserDetails("user")
    public void alienReviewWithUser() throws Exception{
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[1]/h1").string("TestReview1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[1]/video/source").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[2]").string("3"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/span").string("testComment1"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/span[1]/a").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/span").doesNotExist());
    }
    @Test
    @WithUserDetails("admin")
    public void alienReviewWithAdmin() throws Exception{
        this.mockMvc.perform(get("/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[1]/h1").string("TestReview2"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[1]/video/source").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[2]").string("10"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/span").string("testComment3"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/span").exists());
    }
    @Test
    @WithUserDetails("user")
    public void ownerReviewWithUser() throws Exception{
        this.mockMvc.perform(get("/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[1]/h1").string("TestReview2"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[1]/video/source").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[2]").string("7.5"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/span").string("testComment3"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/a").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/span").exists());
    }
    @Test
    @WithUserDetails("admin")
    public void ownerReviewWithAdmin() throws Exception{
        this.mockMvc.perform(get("/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[3]/div[1]/h1").string("TestReview1"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[1]/video/source").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[2]/div/div[2]").string("9"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[1]/div/div[2]/span").string("testComment1"))
                .andExpect(xpath("/html/body/div[2]/div[2]/div/div[3]/div[2]/div/div[2]/span[1]/a").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/a").exists())
                .andExpect(xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[1]/span[2]/span/span").exists());
    }
    @Test
    public void alienProfile() throws Exception{
        this.mockMvc.perform(get("/user?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[2]").string("UserNickname"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[2]/div").nodeCount(3))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").doesNotExist())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/avatar.jpeg']").exists());
    }
    @Test
    @WithUserDetails("admin")
    public void alienProfileWithAdmin() throws Exception{
        this.mockMvc.perform(get("/user?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[3]").string("UserNickname"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(3))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[3]/div/a").string("Заблокировать пользователя"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/avatar.jpeg']").exists());
    }
    @Test
    @WithUserDetails("user")
    public void adminProfile() throws Exception{
        this.mockMvc.perform(get("/user?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[3]").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[2]").string("Да я админ, базару нет, но подписчики-то вы, без вас этот группа ничто"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(3))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").doesNotExist())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/users/icon.jpg']").exists());
    }
    @Test
    @WithUserDetails("user")
    public void ownerProfileUser() throws Exception{
        this.mockMvc.perform(get("/user?id=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[3]").string("UserNickname"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(3))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[4]/div/a[1]").string("Изменить страницу"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[4]/div/a[2]").string("Удалить страницу"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[2]/div/button").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/a").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/avatar.jpeg']").exists());
    }
    @Test
    @WithUserDetails("admin")
    public void ownerProfileAdmin() throws Exception{
        this.mockMvc.perform(get("/user?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[3]").string("AdminNickname"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div").nodeCount(3))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[4]/div/a").doesNotExist())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[2]/div/button").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/a").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").exists())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/users/icon.jpg']").exists());
    }
    @Test
    public void bannedUserProfile() throws Exception{
        this.mockMvc.perform(get("/user?id=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[1]/div[2]").string("BannedUser"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[2]/div").nodeCount(2))
                .andExpect(xpath("/html/body/div[3]/div/div/div[2]/div[3]/div[1]/div/div/div/div/div[3]/div/span").doesNotExist())
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[2]/div[2]").string("Этот пользователь заблокирован"))
                .andExpect(xpath("/html/body/div[3]/div/div/div[1]/div[1]/img[@src='/image/banned.jpg']").exists());
    }
    @Test
    public void addReviewWithGuest() throws Exception{
        this.mockMvc.perform(get("/add-review"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("user")
    public void addReviewWithAuthority() throws Exception{
        this.mockMvc.perform(get("/add-review"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Трейлер")))
                .andExpect(content().string(containsString("Фильм")))
                .andExpect(content().string(containsString("Год")))
                .andExpect(content().string(containsString("Режисер")))
                .andExpect(content().string(containsString("Страна")))
                .andExpect(content().string(containsString("В ролях")))
                .andExpect(content().string(containsString("Постер")))
                .andExpect(xpath("//*[@id=\"name\"][@placeholder='Название']").exists())
                .andExpect(xpath("//*[@id=\"text\"][@placeholder='Текст рецензии']").exists())
                .andExpect(xpath("/html/body/div/div[1]/form/div[3]/div[3]/div[2]/input[@value='Применить']").exists());
    }
    @Test
    public void editReviewWithGuest() throws Exception{
        this.mockMvc.perform(get("/edit-review").param("id", "2"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("user")
    public void editReviewWithAuthority() throws Exception{
        this.mockMvc.perform(get("/edit-review").param("id", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//*[@id=\"trailerUrl\"][@value='https://www.film.ru/sites/default/files/trailers/1615896/The-Shining-Trailer.mp4']").exists())
                .andExpect(xpath("//*[@id=\"filmName\"][@value='testName2']").exists())
                .andExpect(xpath("//*[@id=\"year\"][@value='2023']").exists())
                .andExpect(xpath("//*[@id=\"director\"][@value='testDirector2']").exists())
                .andExpect(xpath("/html/body/div/div[1]/form/div[2]/div[2]/div[2]/div[4]/select/option[2][@selected='selected']").exists())
                .andExpect(xpath("//*[@id=\"cast\"][@value='testCast2']").exists())
                .andExpect(xpath("//*[@id=\"poster\"][@value='https://www.kino-teatr.ru/movie/posters/big/6/10716.jpg']").exists())
                .andExpect(xpath("/html/body/div/div[1]/form/div[2]/div[2]/div[2]/div[1]/a").string("UserNickname"))
                .andExpect(xpath("//*[@id=\"name\"][@value='TestReview2']").exists())
                .andExpect(xpath("//*[@id=\"text\"]").string("text text text text text"))
                .andExpect(xpath("/html/body/div/div[1]/form/div[3]/div[3]/div[2]/input[@value='Применить']").exists());
    }
    @Test
    @WithUserDetails("user")
    public void editAlienReview() throws Exception{
        this.mockMvc.perform(get("/edit-review").param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    @WithUserDetails("admin")
    public void usersTest() throws Exception{
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div[1]/aside/div/div").nodeCount(2))
                .andExpect(xpath("/html/body/div[1]/aside/div/div/div/div[2]/div[1]/a").exists())
                .andExpect(xpath("/html/body/div[1]/aside/div/div[1]/div/div[1]/img[@src='/image/avatar.jpeg']").exists())
                .andExpect(xpath("/html/body/div[1]/aside/div/div[2]/div/div[1]/img[@src='/image/banned.jpg']").exists());
    }
    @Test
    public void usersWithGuest() throws Exception{
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
    @Test
    @WithUserDetails("user")
    public void usersWithUser() throws Exception{
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
