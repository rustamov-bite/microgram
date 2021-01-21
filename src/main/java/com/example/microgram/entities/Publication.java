package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "publications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Publication {
    @Id
    @Indexed
    private String id;

    private String image;

    private String description;

    private LocalDate publicationDate;

    List<Like> publicationLikes;

    List<Comment> publicationComments;
}
