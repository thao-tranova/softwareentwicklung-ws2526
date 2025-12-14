package de.othr.jwtproject.model.dto;

import de.othr.jwtproject.model.Authority;

import java.io.Serializable;
import java.util.ArrayList;

public class RegisterRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
  private String email;
  private ArrayList<Authority> myauthorities = new ArrayList<>();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<Authority> getMyauthorities() {
    return myauthorities;
  }

  public void setMyauthorities(ArrayList<Authority> myauthorities) {
    this.myauthorities = myauthorities;
  }
}
