package com.example.microgram.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Publication {
    private String image;

    private String description;

    private LocalDate publicationDate;

    List<Like> publicationLikes;

    List<Comment> publicationComments;

    private User user;
}
