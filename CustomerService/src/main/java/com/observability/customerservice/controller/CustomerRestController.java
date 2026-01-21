package com.observability.customerservice.controller;

import com.observability.customerservice.entities.Customer;
import com.observability.customerservice.model.Post;
import com.observability.customerservice.repository.ICustomerRepository;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
  private final ICustomerRepository customerRepository;
  private final RestClient restClient;

  public CustomerRestController(
      ICustomerRepository customerRepository, RestClient.Builder restClient) {
    this.customerRepository = customerRepository;
    this.restClient = restClient.baseUrl("https://jsonplaceholder.typicode.com").build();
  }

  @GetMapping("/customers")
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @GetMapping("/posts")
  public List<Post> allPosts() {
    return restClient
        .get()
        .uri("/posts")
        .retrieve()
        .body(new ParameterizedTypeReference<List<Post>>() {});
  }
}
