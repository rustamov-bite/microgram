package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Like {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @DBRef
    private User user;
    @DBRef
    private Publication publication;
    private LocalDate likeDate;

    @Override
    public String toString() {
        return "Like{" +
                "user=" + user +
                ", publication=" + publication +
                ", likeDate=" + likeDate +
                '}';
    }
}
