package com.example.microgram.dto;

import com.example.microgram.entities.Comment;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDto {
    private final User user;
    private final Publication publication;
    private final String text;
    private final LocalDate commentDate;

    public static CommentDto getCommentDto(Comment comment) {
        return CommentDto.builder()
                .user(comment.getUser())
                .publication(comment.getPublication())
                .text(comment.getText())
                .commentDate(comment.getCommentDate())
                .build();
    }

    public static List<CommentDto> getListCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment c : comments) {
            commentDtos.add(getCommentDto(c));
        }
        return commentDtos;
    }
}
