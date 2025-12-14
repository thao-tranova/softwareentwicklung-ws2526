package de.othr.jwtproject.config;

import de.othr.jwtproject.model.Authority;
import de.othr.jwtproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
  private static final long serialVersionUID = 1L;
  private String userName;
  private String password;
  private boolean active;
  private List<GrantedAuthority> authorities;

  public MyUserDetails(User user) {
    // TODO Auto-generated constructor stub
    this.userName = user.getLogin();
    this.password = user.getPassword();
    System.out.println("password of the user is=" + password);
    System.out.println("userName of the user is=" + this.userName);
    this.active = (user.getActive() > 0) ? true : false;
    List<Authority> myauthorities = (List<Authority>) user.getMyauthorities();
    System.out.println("the user "+ user.getLogin() + " has " + myauthorities.size() + " authorities");
    authorities = new ArrayList<>();
    for (int i=0; i< myauthorities.size(); i++){
      authorities.add(new SimpleGrantedAuthority(myauthorities.get(i).getDescription().toUpperCase()));
      System.out.println("the profile" + i + " of " + user.getLogin() + " is " + myauthorities.get(0).getDescription());
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.userName;
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
    return this.active;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
