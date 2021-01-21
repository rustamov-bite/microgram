package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "follows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Follow {
    @Id
    private String id;

    @DBRef
    @Field(name = "follows")
    private User follows;

    @DBRef
    @Field(name = "isFollowed")
    private User isFollowed;

    private LocalDate followDate;
}
