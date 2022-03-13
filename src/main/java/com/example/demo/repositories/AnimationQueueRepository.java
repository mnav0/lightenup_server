package com.example.demo.repositories;

import com.example.demo.models.AnimationQueue;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface AnimationQueueRepository extends CrudRepository<AnimationQueue, Integer> {
    Iterable<AnimationQueue> findAll(Sort id);
}
