package com.example.microgram.controllers;

import com.example.microgram.entities.Comment;
import com.example.microgram.entities.Like;
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
    public boolean existsByLike(@RequestParam("userId") String userId,
                                @RequestParam("publicationId") String publicationId) {
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

    @PostMapping("/addPublication/{userId}")
    public Publication addPublication(@PathVariable("userId") String userId, @RequestParam("image") String image,
                                      @RequestParam("description") String description) {
        return mainService.addPublication(userId, image, description);
    }

    @PostMapping("/registration")
    public User addUser(@RequestParam("name") String name, @RequestParam("login") String login,
                        @RequestParam("email") String email, @RequestParam("password") String password) {
        return mainService.addUser(name, login, email, password);
    }

    @GetMapping("/login")
    public User loginUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        return mainService.loginUser(login, password);
    }

    @DeleteMapping("/deletePublication/{userId}")
    public List<Publication> deletePublication(@PathVariable("userId") String userId,
                                               @RequestParam("publicationId") String publicationId) {
        return mainService.deletePublication(userId, publicationId);
    }

    @PostMapping("/addComment/{userId}")
    public Comment addComment(@PathVariable("userId") String userId,
                              @RequestParam("publicationId") String publicationId,
                              @RequestParam("text") String text) {
        return mainService.addComment(userId, publicationId, text);
    }
    
    @DeleteMapping("/deleteComment/{userId}")
    public List<Comment> deleteComment(@PathVariable("userId") String userId,
                                       @RequestParam("commentId") String commentId) {
        return mainService.deleteComment(userId, commentId);
    }

    @PostMapping("/follow/{userId}")
    public List<Publication> followUser(@PathVariable("userId") String userId,
                                        @RequestParam("isFollowedId") String isFollowedId) {
        return mainService.followUser(userId, isFollowedId);
    }

    @PostMapping("/like/{userId}")
    public Like likePublication(@PathVariable("userId") String userId,
                                      @RequestParam("publicationId") String publicationId) {
        return mainService.likePublication(userId, publicationId);
    }
}
