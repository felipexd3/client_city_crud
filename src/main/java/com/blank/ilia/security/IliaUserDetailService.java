package com.blank.ilia.security;

import com.blank.ilia.model.Role;
import com.blank.ilia.model.UserAccess;
import com.blank.ilia.model.UserSecurity;
import com.blank.ilia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class IliaUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurity userSecurity = userRepository.findByUsernameAndActiveIsTrue(username).orElseThrow(() -> new RuntimeException());
        return new UserAccess(userSecurity.getName(), userSecurity.getUsername(), userSecurity.getPassword(), authorities(userSecurity));
    }

    public Collection<? extends GrantedAuthority> authorities(UserSecurity userSecurity) {
        return authorities(userSecurity.getRole());
    }

    public Collection<? extends GrantedAuthority> authorities(List<Role> roles) {
        Collection<GrantedAuthority> auths = new ArrayList<>();

        for (Role role: roles) {
                auths.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return auths;
    }
}
