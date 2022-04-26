package com.example.kiennt_demo2.security.impl;

import com.example.kiennt_demo2.entity.Role;
import com.example.kiennt_demo2.entity.User;
import com.example.kiennt_demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Kiểm tra xem user có tồn tại trong database không?
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // Khi đã có user rồi thì mình query xem user đó có những quyền gì (Admin hay User)
        // [ROLE_USER, ROLE_ADMIN,..]
        UserDetailsImpl userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword(), getAuthorities(user));
        return userDetails;
    }

    public List<? extends GrantedAuthority> getAuthorities(User user) {
        Set<Role> role = user.getRoles();

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role listRole : role) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(listRole.getName().name());
            authorities.add(grantedAuthority);
        }
        //authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

}
