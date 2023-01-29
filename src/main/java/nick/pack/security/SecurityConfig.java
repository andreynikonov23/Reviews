package nick.pack.security;

import nick.pack.model.Permission;
import nick.pack.model.RoleEnum;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
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
                antMatchers(HttpMethod.GET,"/view/**").permitAll().
                anyRequest().authenticated().
                and().
                    formLogin().
                    loginPage("/login").
                    defaultSuccessUrl("/api/test").
                and().
                    logout().
                        logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")).
                        invalidateHttpSession(true).
                        clearAuthentication(true).
                        deleteCookies("JSESSIONID").
                        logoutSuccessUrl("/view/");
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
