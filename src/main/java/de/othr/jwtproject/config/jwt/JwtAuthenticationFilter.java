package de.othr.jwtproject.config.jwt;

import de.othr.jwtproject.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Additional filter to intercept the request and check if there is a token on its header and validate it.
//If there is, then it will try to validate the token.
//If there is not, the user should authenticate himself in the sequence.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  @Autowired
  private MyUserDetailsService userDetailsService;
  @Autowired
  private JwtService jwtService;
  @Override
  protected void doFilterInternal(
     jakarta.servlet.http.HttpServletRequest request,
     HttpServletResponse response,
     FilterChain chain)
     throws jakarta.servlet.ServletException, IOException {
    //this comes from the header of the request
    final String authorizationHeader = request.getHeader("Authorization");

    String username = null;
    String jwt = null;
    //if the authorization content in the header is not null and starts with "Bearer "...
    //let us extract from the header the token and the username...
    if (authorizationHeader != null && authorizationHeader.startsWith(jwtService.getPREFIX())) {
      jwt = authorizationHeader.substring(jwtService.getPREFIX().length());
      username = jwtService.extractUsername(jwt);
    }
    //if the user is not empty and the user is not yet authenticated, we will update the security context holder with the token
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      //trying to find the user in the DB
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      //if the token is valid, we will proceed with the login...
      if (jwtService.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
           userDetails, null, userDetails.getAuthorities());
        //putting the information of the request object in the usernamePasswordAuthenticationToken
        usernamePasswordAuthenticationToken
           .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //informing to the SecurityContextHolder that the user is authenticated
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    //continue with the chain of filters
    chain.doFilter(request, response);
  }
}
