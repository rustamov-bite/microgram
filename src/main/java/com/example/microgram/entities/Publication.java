package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "publications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Publication {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @DBRef
    private User user;

    private String imageId;

    private String description;

    private LocalDate publicationDate;
}
