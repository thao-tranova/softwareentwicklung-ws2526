package de.othr.restproject.service;

import de.othr.restproject.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostClient {
  @Autowired
  private RestTemplate restTemplate;

  public List<Post> getPosts() {
    String apiUrl = "https://jsonplaceholder.typicode.com/posts";
    ResponseEntity<List<Post>> response = restTemplate.exchange(
       apiUrl,
       HttpMethod.GET,
       null,
       new ParameterizedTypeReference<List<Post>>() {
       }
    );
    // This was in the original exercise sheet, but causes problems with Jackson deserialization
    // ResponseEntity<List<Post>> response = restTemplate.getForEntity(apiUrl, List.class);
    if (response.getStatusCode() == HttpStatus.OK) {
      return response.getBody();
    } else {
      throw new RuntimeException("Failed to fetch posts: " + response.getStatusCode());
    }
  }
}
