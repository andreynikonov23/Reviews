package nick.pack.security;

import nick.pack.service.UserService;
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

@Configuration
@EnableWebSecurity
//@EnableJdbcHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Service
    private final UserService service;
    @Autowired
    public SecurityConfig(UserService service) {
        this.service = service;
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
        http.
                csrf().disable().
                authorizeRequests().
                    antMatchers(HttpMethod.GET,"/**").permitAll().
                    antMatchers(HttpMethod.POST, "/signup", "/activate", "/mail-send", "/recover-request").permitAll().
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
}
