package com.example.microgram.dto;

import com.example.microgram.entities.Like;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
public class LikeDto {
    private final User user;
    private final Publication publication;
    private final LocalDate likeDate;

    public static LikeDto getLikeDto(Like like) {
        return LikeDto.builder()
                .user(like.getUser())
                .publication(like.getPublication())
                .likeDate(like.getLikeDate())
                .build();
    }
}
