package com.example.demo.services;

import com.example.demo.models.Hello;
import com.example.demo.repositories.HelloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
  @Autowired
  HelloRepository repository;
  @GetMapping("/api/hello")
  public Iterable<Hello> findAllHellos() {
    return repository.findAll();
  }

}
