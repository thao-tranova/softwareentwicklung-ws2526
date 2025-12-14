package de.othr.jwtproject.service;

import de.othr.jwtproject.config.MyUserDetails;
import de.othr.jwtproject.model.User;
import de.othr.jwtproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    Optional<User> oUser= userRepository.findUserByLogin(username);
    oUser.orElseThrow(()-> new UsernameNotFoundException("Not found " + username));
    System.out.println("User found at the UserDetailsService="+ oUser.get().getLogin());
    return new MyUserDetails(oUser.get());
  }
}
