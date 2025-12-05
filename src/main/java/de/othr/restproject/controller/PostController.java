package de.othr.restproject.controller;

import de.othr.restproject.model.Post;
import de.othr.restproject.service.PostClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostClient postClient;

  @GetMapping
  public List<Post> getPosts() {
    return postClient.getPosts();
  }
}