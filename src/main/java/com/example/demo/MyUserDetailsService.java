package com.example.demo;

import com.example.demo.models.MyUserDetails;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not Found:  " +username));
        return user.map(MyUserDetails::new).get();
    }
}
