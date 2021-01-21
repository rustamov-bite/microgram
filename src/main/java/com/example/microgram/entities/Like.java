package com.example.microgram.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Like {
    User user;

    Publication publication;

    private LocalDate likeDate;
}
