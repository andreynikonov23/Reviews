package nick.pack.security;

import nick.pack.model.User;
import nick.pack.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;

//Класс конфигурации
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Logger
    private static final Logger logger = Logger.getLogger(SecurityConfig.class);
    //Service
    private final UserService service;
    @Autowired
    public SecurityConfig(UserService service) {
        this.service = service;
    }

    //destroy-method
    @PreDestroy
    public void destroy(){
        logger.debug("shutting down the server");
    }


    //Security configuration
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().
                antMatchers("/css/**").
                antMatchers("/image/**").
                antMatchers("/script/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("performing security configuration with parameters (csrf=disable");
        http.
                csrf().disable().
                authorizeRequests().
                    antMatchers(HttpMethod.GET,"/**").permitAll().
                    antMatchers(HttpMethod.POST, "/signup", "/activate", "/mail-send", "/recover-request", "/edit-password").permitAll().
                    anyRequest().authenticated().
                and().
                    rememberMe().
                        key("secretKey").
                        userDetailsService(service).
                and().
                    formLogin().
                        loginPage("/login").permitAll().
                        defaultSuccessUrl("/").
                and().
                    logout().
                        invalidateHttpSession(true).
                        clearAuthentication(true).
                        deleteCookies("JSESSIONID").
                        logoutSuccessUrl("/login");
    }


    //Beans
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(service);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public HashMap<String, User> unconfirmedUsers() {
        return new HashMap<>();
    }

    @Bean
    public ArrayList<User> confirmedUsers(){return new ArrayList<>();}

}
