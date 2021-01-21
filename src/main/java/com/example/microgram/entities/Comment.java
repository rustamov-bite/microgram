package com.example.microgram.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Comment {
    private User user;

    private Publication publication;

    private String text;

    private LocalDate commentDate;
}
