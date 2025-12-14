package de.othr.jwtproject.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="authority")
public class Authority implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
