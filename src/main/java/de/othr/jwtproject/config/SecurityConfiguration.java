package de.othr.jwtproject.config;

import de.othr.jwtproject.config.jwt.JwtAuthenticationFilter;
import de.othr.jwtproject.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Autowired
  MyUserDetailsService userDetailsService;

  @Autowired
  private JwtAuthenticationFilter jwtRequestFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(rQ -> {
         rQ.requestMatchers("/api/register ", "/api/users/authenticate").permitAll();
         rQ.requestMatchers("/api/search", "/api/demo", "/api/profile").authenticated();
       })
       .formLogin(form -> form
          //.loginPage("/login") , if you want to have a customized login page….
          .loginProcessingUrl("/login")
          .defaultSuccessUrl("/", true)
          .permitAll())
       // where the user goes after the logout
       .logout(logout -> logout
          .logoutSuccessUrl("/login")
          .invalidateHttpSession(true)
          .permitAll());

    http
       .headers(headers -> headers
          .frameOptions(frameOptions -> frameOptions
             .sameOrigin())
       );

    http.csrf(AbstractHttpConfigurer::disable);

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
     AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
    //return NoOpPasswordEncoder.getInstance();
  }
}
