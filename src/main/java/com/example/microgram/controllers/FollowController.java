package com.example.microgram.controllers;

import com.example.microgram.dto.PublicationDto;
import com.example.microgram.entities.User;
import com.example.microgram.services.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follows")
@AllArgsConstructor
public class FollowController {
    private final FollowService followService;

    @GetMapping("/feedForUser")
    public List<PublicationDto> feedForUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return PublicationDto.getListOfPublicationDto(followService.feedForUser(user.getId()));
    }

    @PostMapping("/follow")
    public List<PublicationDto> followUser(Authentication authentication,
                                           @RequestParam("isFollowedId") String isFollowedId) {
        User user = (User) authentication.getPrincipal();
        return PublicationDto.getListOfPublicationDto(followService.followUser(user.getId(), isFollowedId));
    }
}
