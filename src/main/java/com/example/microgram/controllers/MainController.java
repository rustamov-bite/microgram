package com.example.microgram.controllers;

import com.example.microgram.dto.CommentDto;
import com.example.microgram.dto.LikeDto;
import com.example.microgram.dto.PublicationDto;
import com.example.microgram.dto.UserDto;
import com.example.microgram.entities.Comment;
import com.example.microgram.entities.Like;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import com.example.microgram.services.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/getUserByName")
    public UserDto getUserByName(@RequestParam("userName") String userName) {
        return UserDto.getUserDto(mainService.findUserByName(userName));
    }

    @GetMapping("/getUserByLogin")
    public UserDto getUserByLogin(@RequestParam("userLogin") String userLogin) {
        return UserDto.getUserDto(mainService.findUserByLogin(userLogin));
    }

    @GetMapping("/getUserByEmail")
    public UserDto getUserByEmail(@RequestParam("userEmail") String email) {
        return UserDto.getUserDto(mainService.findUserByMail(email));
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
    public List<PublicationDto> feedForUser(@RequestParam("userId") String userId) {
        return PublicationDto.getListOfPublicationDto(mainService.feedForUser(userId));
    }

    @GetMapping("/publicationsOfOneUser")
    public List<PublicationDto> publicationsOfOneUser(@RequestParam("userId") String userId) {
        return PublicationDto.getListOfPublicationDto(mainService.getPublicationsOfOneUser(userId));
    }

    @PostMapping("/publication/{userId}")
    public PublicationDto addPublication(@PathVariable("userId") String userId, @RequestParam("image") String image,
                                      @RequestParam("description") String description) {
        return PublicationDto.getPublicationDto(mainService.addPublication(userId, image, description));
    }

    @PostMapping("/registration")
    public UserDto addUser(@RequestParam("name") String name, @RequestParam("login") String login,
                        @RequestParam("email") String email, @RequestParam("password") String password) {
        return UserDto.getUserDto(mainService.addUser(name, login, email, password));
    }

    @GetMapping("/login")
    public UserDto loginUser(@RequestParam("login") String login, @RequestParam("password") String password) {
        return UserDto.getUserDto(mainService.loginUser(login, password));
    }

    @DeleteMapping("/publication/{userId}")
    public List<PublicationDto> deletePublication(@PathVariable("userId") String userId,
                                               @RequestParam("publicationId") String publicationId) {
        return PublicationDto.getListOfPublicationDto(mainService.deletePublication(userId, publicationId));
    }

    @PostMapping("/comment/{userId}")
    public CommentDto addComment(@PathVariable("userId") String userId,
                                 @RequestParam("publicationId") String publicationId,
                                 @RequestParam("text") String text) {
        return CommentDto.getCommentDto(mainService.addComment(userId, publicationId, text));
    }
    
    @DeleteMapping("/comment/{userId}")
    public List<CommentDto> deleteComment(@PathVariable("userId") String userId,
                                       @RequestParam("commentId") String commentId) {
        return CommentDto.getListCommentDto(mainService.deleteComment(userId, commentId));
    }

    @PostMapping("/follow/{userId}")
    public List<PublicationDto> followUser(@PathVariable("userId") String userId,
                                        @RequestParam("isFollowedId") String isFollowedId) {
        return PublicationDto.getListOfPublicationDto(mainService.followUser(userId, isFollowedId));
    }

    @PostMapping("/like/{userId}")
    public LikeDto likePublication(@PathVariable("userId") String userId,
                                      @RequestParam("publicationId") String publicationId) {
        return LikeDto.getLikeDto(mainService.likePublication(userId, publicationId));
    }
}
