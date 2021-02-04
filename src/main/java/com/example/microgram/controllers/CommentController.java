package com.example.microgram.controllers;

import com.example.microgram.dto.CommentDto;
import com.example.microgram.entities.User;
import com.example.microgram.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public CommentDto addComment(Authentication authentication,
                                 @RequestParam("publicationId") String publicationId,
                                 @RequestParam("text") String text) {
        User user = (User) authentication.getPrincipal();
        return CommentDto.getCommentDto(commentService.addComment(user.getId(), publicationId, text));
    }

    @DeleteMapping("/comment")
    public List<CommentDto> deleteComment(Authentication authentication,
                                          @RequestParam("commentId") String commentId) {
        User user = (User) authentication.getPrincipal();
        return CommentDto.getListCommentDto(commentService.deleteComment(user.getId(), commentId));
    }
}
