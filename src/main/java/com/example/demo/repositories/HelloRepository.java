package com.example.demo.repositories;

import com.example.demo.models.Hello;

import org.springframework.data.repository.CrudRepository;

public interface HelloRepository extends CrudRepository<Hello, Integer> {
}
