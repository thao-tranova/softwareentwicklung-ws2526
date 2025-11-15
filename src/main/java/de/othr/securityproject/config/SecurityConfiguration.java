package de.othr.securityproject.config;

import de.othr.securityproject.service.MyUserDetailsService;
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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Autowired
  MyUserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(rQ -> {
      rQ.requestMatchers("/h2-console/**", "/console/**", "/signup/", "/signin/").permitAll();
      rQ.requestMatchers("/images/**", "/js/**", "/webjars/**").permitAll();

      rQ.requestMatchers("/home").permitAll();
      rQ.requestMatchers ("/student/**").hasAnyAuthority("ADMIN","STUDENT");
      rQ.requestMatchers("/admin/**", "/settings/**").hasAuthority("ADMIN") ;

      rQ.requestMatchers("/api/search/", "/api/profile/", "/signout/").authenticated();
    });
    //deprecate code, try to migrate it!
    http.formLogin()
       //.loginPage("/login") , if you want to have a customized login page….
       .and()
       .logout()
       //where the user goes after the logout
       .logoutSuccessUrl("/login")
       .invalidateHttpSession(true)
       .permitAll();

    // If you choose to disable the X-Frame-Options header (not recommended) by setting.headers().frameOptions().disable(), then Spring Security will not add the X-Frame-Options header to the response.
    // This means your application could be rendered in a frame, and also could be vulnerable to Clickjacking attacks.
    // Since the frames in the H2 console UI (such as http://localhost:8080/h2-console/tables.do) are on the same origin as the the H2 console (http://localhost:8080/h2-console), the browser will allow them to be displayed.

    http
       .headers(headers -> headers
          .frameOptions(frameOptions -> frameOptions
             .sameOrigin())
       );

    http.csrf(AbstractHttpConfigurer::disable);
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

