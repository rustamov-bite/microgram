package com.example.microgram.controllers;

import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import com.example.microgram.services.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/getUserByName")
    public User getUserByName(@RequestParam("userName") String userName) {
        return mainService.findUserByName(userName);
    }

    @GetMapping("/getUserByLogin")
    public User getUserByLogin(@RequestParam("userLogin") String userLogin) {
        return mainService.findUserByLogin(userLogin);
    }

    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam("userEmail") String email) {
        return mainService.findUserByMail(email);
    }

    @GetMapping("/existsByMail")
    public boolean existsByMail(@RequestParam("userEmail") String email) {
        return mainService.findExistByMail(email);
    }

    @GetMapping("/existsByLike")
    public boolean existsByLike(@RequestParam("userId") String userId, @RequestParam("publicationId") String publicationId) {
        return mainService.findLike(userId, publicationId);
    }

    @GetMapping("/feedForUser")
    public List<Publication> feedForUser(@RequestParam("userId") String userId) {
        return mainService.feedForUser(userId);
    }

    @GetMapping("/publicationsOfOneUser")
    public List<Publication> publicationsOfOneUser(@RequestParam("userId") String userId) {
        return mainService.getPublicationsOfOneUser(userId);
    }
}
