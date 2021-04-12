package com.student.sec;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service

public class JWTUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DAOUser user = userDao.findByUserid(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found  with username: " + username);
        }

        //Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        //for (roles role : user.getRoles()) {
            //grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        //}
        List<GrantedAuthority> authorityList = user.getRole1().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
        //.map(role -> new SimpleGrantedAuthority("ROLE_ADMIN"))
                .collect(Collectors.toList());
       // List<GrantedAuthority> authorities = getUserAuthority(user.getRole1());
        //buildUserForAuthentication(user, authorities);


        return new org.springframework.security.core.userdetails.User(user.getUserid(), user.getPassword(),
                authorityList);


    }

    public String save(UserDTO user) {
        DAOUser newUser = new DAOUser();
        newUser.setUserid(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setId(user.getId());
        newUser.setRole(user.getRole());
       // return (UserDao) userDao.save(newUser);
        userDao.save(newUser);
        return "User Saved";
    }



}
