package com.example.kiennt_demo2.security.impl;

import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(null, "kiennt1",authorities);
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authentication = this.authenticationProvider.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(token);
//        System.out.println(a.getName());

        return new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
    }


    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public List<? extends GrantedAuthority> getAuthorities(User user) {
        // Mặc định mình sẽ để tất cả là ROLE. Để demo cho đơn giản.

        Set<Role> role = user.getRoles();

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role listRole : role) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(listRole.getName().name());
            authorities.add(grantedAuthority);
        }
        //authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
