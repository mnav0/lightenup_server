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
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;

@CrossOrigin(origins = "*")
@RestController
public class AnimationQueueService {
    @Autowired
    AnimationQueueRepository queueRepository;
    @Autowired
    AnimationTypesRepository typeRepository;

    @GetMapping("/getQueue")
    public Iterable<AnimationQueue> getQueue() {
        return queueRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @GetMapping("/deleteFromQueue/{queueId}")
    public Iterable<AnimationQueue> deleteAnimation(
            @PathVariable("queueId") Integer queueId) {
        queueRepository.deleteById(queueId);
        return queueRepository.findAll();
    }

    @GetMapping("/setFinished/{queueId}")
    public AnimationQueue setFinished(
            @PathVariable("queueId") Integer queueId) {
        try {
            AnimationQueue animQueue = queueRepository.findById(queueId).get();
            animQueue.setFinished(true);
            queueRepository.save(animQueue);
            return animQueue;
        } catch (Exception e) {
            System.out.println("did not get an anim with id " + queueId + ", " + e);
            return new AnimationQueue();
        }
    }

    private String playAnimation(int animTypeId, int queueId) {
        final String uri = "https://animserver-navracruz.pitunnel.com/playAnim?type=" + animTypeId + "&id=" + queueId;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    private String traverseQueue() {
        Iterator<AnimationQueue> currQueue = queueRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).iterator();

        if (currQueue.hasNext()) {
            AnimationQueue first = currQueue.next();
            System.out.println(first.getId() + " is the first and playing is " + first.getPlaying() + ", finished is " + first.getFinished());
            if (!first.getPlaying()) {
                first.setPlaying(true);
                queueRepository.save(first);

                int animId = first.getType();
                System.out.println(animId + " about to play");

                String response = playAnimation(animId, first.getId());

                System.out.println(animId + " done playing, response: ");
                System.out.println(response);
                queueRepository.deleteById(first.getId());
                traverseQueue();
            } else if (first.getFinished()) {
                queueRepository.deleteById(first.getId());
                traverseQueue();
            } else {
                return "something is playing or hasn't yet finished";
            }
        } else {
            return "the queue is now empty";
        }
        return "queue has been traversed";
    }

    @GetMapping("/addToQueue/{typeId}")
    public String createAnimation(
            @PathVariable("typeId") Integer typeId
    ) {
        AnimationTypes type = typeRepository.findById(typeId).get();
        AnimationQueue anim = new AnimationQueue(type);
        queueRepository.save(anim);

        return traverseQueue();
    }

}
