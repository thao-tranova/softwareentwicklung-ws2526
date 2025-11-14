package de.othr.securityproject.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;
  private String login;
  private String password;
  private int active;
  private String email;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
     name="userauthority",
     joinColumns = @JoinColumn(name="iduser"),
     inverseJoinColumns = @JoinColumn(name="idauthority")
  )
  //my own type of authority, not from spring security
  private List<Authority> myauthorities = new ArrayList<Authority>();

  public Long getID() {
    return ID;
  }

  public void setID(Long ID) {
    this.ID = ID;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Authority> getMyauthorities() {
    return myauthorities;
  }

  public void setMyauthorities(List<Authority> myauthorities) {
    this.myauthorities = myauthorities;
  }
}

