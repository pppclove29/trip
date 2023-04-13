package com.project.trip.global.oauth;

import com.project.trip.user.entity.Role;
import com.project.trip.user.entity.User;
import jdk.jfr.Description;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomOauthUser implements UserDetails, OAuth2User {

    private User user;

    public CustomOauthUser(OAuth2User oAuth2User) {
        this.user = User.getTempUser(oAuth2User);
    }

    public CustomOauthUser(User user) {
        this.user = user;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> list = new ArrayList<>();
        list.add((GrantedAuthority) () -> user.getRole().getRole());

        return list;
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getRedirect() {
        if (user.getRole() == Role.TEMP)
            return "/users";

        return "/";
    }
    public Role getRole(){
        return user.getRole();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Deprecated
    @Description("Use getUserName instead")
    @Override
    public String getName() {
        return null;
    }
}
