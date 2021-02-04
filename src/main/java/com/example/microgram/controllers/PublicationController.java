package com.example.microgram.controllers;

import com.example.microgram.dto.PublicationDto;
import com.example.microgram.entities.User;
import com.example.microgram.services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/publications")
@AllArgsConstructor
public class PublicationController {
    private final PublicationService publicationService;
    @GetMapping("/publicationsOfOneUser")
    public List<PublicationDto> publicationsOfOneUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return PublicationDto.getListOfPublicationDto(publicationService.getPublicationsOfOneUser(user.getId()));
    }

    @PostMapping("/publication")
    public ResponseEntity<Void> addPublication(Authentication authentication, @RequestParam("image") MultipartFile image,
                                         @RequestParam("description") String description) {
        User user = (User) authentication.getPrincipal();
        if(publicationService.addPublication(user.getId(), image, description)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/publication")
    public List<PublicationDto> deletePublication(Authentication authentication,
                                                  @RequestParam("publicationId") String publicationId) {
        User user = (User) authentication.getPrincipal();
        return PublicationDto.getListOfPublicationDto(publicationService.deletePublication(user.getId(), publicationId));
    }
}
