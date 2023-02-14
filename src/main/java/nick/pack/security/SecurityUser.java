package nick.pack.security;

import nick.pack.model.StatusEnum;
import nick.pack.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class SecurityUser implements UserDetails {
    private String login;
    private String password;
    private boolean isActive;
    private Set<SimpleGrantedAuthority> authorities;


    public SecurityUser(String login, String password, Set<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.login = login;
        this.password = password;
        this.isActive = isActive;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user) {

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                user.getStatus().getStatusEnum().equals(StatusEnum.ACTIVE),
                user.getRole().getRoleName().getAuthorities());
    }
}
