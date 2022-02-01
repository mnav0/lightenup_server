package com.example.demo.services;

import com.example.demo.models.AnimationQueue;
import com.example.demo.models.AnimationTypes;
import com.example.demo.repositories.AnimationQueueRepository;

import com.example.demo.repositories.AnimationTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class AnimationQueueService {
    @Autowired
    AnimationQueueRepository queueRepository;
    @Autowired
    AnimationTypesRepository typeRepository;

    @GetMapping("/getQueue")
    public Iterable<AnimationQueue> getQueue() {
        return queueRepository.findAll();
    }

    @GetMapping("/deleteFromQueue/{queueId}")
    public Iterable<AnimationQueue> deleteAnimation(
            @PathVariable("queueId") Integer queueId) {
        queueRepository.deleteById(queueId);
        return queueRepository.findAll();
    }

    @GetMapping("/addToQueue/{typeId}")
    public AnimationQueue createAnimation(
            @PathVariable("typeId") Integer typeId
    ) {
        AnimationTypes type = typeRepository.findById(typeId).get();
        AnimationQueue anim = new AnimationQueue(type);
        queueRepository.save(anim);
        return anim;
    }

}
