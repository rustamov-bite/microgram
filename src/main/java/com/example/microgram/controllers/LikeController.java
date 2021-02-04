package com.example.microgram.controllers;

import com.example.microgram.dto.LikeDto;
import com.example.microgram.entities.User;
import com.example.microgram.services.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/existsByLike")
    public boolean existsByLike(Authentication authentication,
                                @RequestParam("publicationId") String publicationId) {
        User user = (User) authentication.getPrincipal();
        return likeService.findLike(user.getId(), publicationId);
    }

    @PostMapping("/like")
    public LikeDto likePublication(Authentication authentication,
                                   @RequestParam("publicationId") String publicationId) {
        User user = (User) authentication.getPrincipal();
        return LikeDto.getLikeDto(likeService.likePublication(user.getId(), publicationId));
    }
}
