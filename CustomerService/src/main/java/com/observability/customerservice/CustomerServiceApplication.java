package com.observability.customerservice;

import com.observability.customerservice.entities.Customer;
import com.observability.customerservice.repository.ICustomerRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(ICustomerRepository customerRepository) {
    log.info("================= Initialization ================");
    return args -> {
      List<Customer> customers =
          List.of(
              Customer.builder()
                  .firstName("custm1")
                  .lastName("cust1")
                  .email("cust1@gmail.com")
                  .build(),
              Customer.builder()
                  .firstName("custm2")
                  .lastName("cust2")
                  .email("cust2@gmail.com")
                  .build(),
              Customer.builder()
                  .firstName("cust3")
                  .lastName("cust3")
                  .email("cust3@gmail.com")
                  .build());
      customerRepository.saveAll(customers);
    };
  }
}
