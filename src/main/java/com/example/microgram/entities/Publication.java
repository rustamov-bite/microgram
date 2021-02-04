package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "publications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Publication {
    @Id
    private String id;

    @DBRef
    private User user;

    private String imageId;

    private String description;

    private LocalDate publicationDate;
}
