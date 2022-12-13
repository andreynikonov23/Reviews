package nick.pack.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum RoleEnum {
    USER(Set.of(Permission.CRUD)),
    ADMIN(Set.of(Permission.CRUD, Permission.BLOCKING, Permission.VIEWUSER));

    private final Set<Permission> permissions;

    RoleEnum(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities = permissions.stream().map(x -> new SimpleGrantedAuthority(x.getPermission())).collect(Collectors.toSet());
        return authorities;
    }

}
