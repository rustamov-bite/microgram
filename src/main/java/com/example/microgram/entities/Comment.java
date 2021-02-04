package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Comment {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    @DBRef
    private User user;
    @DBRef
    private Publication publication;

    private String text;

    private LocalDate commentDate;

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", publication=" + publication +
                ", text='" + text + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
