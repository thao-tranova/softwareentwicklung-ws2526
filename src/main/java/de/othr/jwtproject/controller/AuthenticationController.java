package de.othr.jwtproject.controller;

import de.othr.jwtproject.config.jwt.JwtService;
import de.othr.jwtproject.model.Authority;
import de.othr.jwtproject.model.User;
import de.othr.jwtproject.model.dto.AuthenticationRequest;
import de.othr.jwtproject.model.dto.AuthenticationResponse;
import de.othr.jwtproject.model.dto.RegisterRequest;
import de.othr.jwtproject.repository.AuthorityRepository;
import de.othr.jwtproject.repository.UserRepository;
import de.othr.jwtproject.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  MyUserDetailsService userDetailsService;
  @Autowired
  JwtService jwtServiceUtils;
  @Autowired
  BCryptPasswordEncoder encoder;
  @Autowired
  UserRepository userRepository;
  @Autowired
  AuthorityRepository authorityRepository;

  @PostMapping("/post")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
    User user = new User();
    user.setActive(1);
    user.setEmail(registerRequest.getEmail());
    user.setLogin(registerRequest.getUsername());
    user.setMyauthorities(new ArrayList<Authority>());
    for (int i = 0; i < registerRequest.getMyauthorities().size(); i++) {
      System.out.println("received auth " + i + " " + registerRequest.getMyauthorities().get(i).getId());
      Optional<Authority> authorityOp = authorityRepository.findById(registerRequest.getMyauthorities().get(i).getId());
      user.getMyauthorities().add(authorityOp.get());
      System.out.println(authorityOp.get().getDescription());
    }
    user.setPassword(encoder.encode(registerRequest.getPassword()));
    user = userRepository.save(user);
    System.out.println("saved the user per API..");
    System.out.println(user.getLogin());
    UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
    String jwt = jwtServiceUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @RequestMapping(value = "/users/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
            authenticationRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password!", e);
    }
    UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String jwt = jwtServiceUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
