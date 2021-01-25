package com.example.microgram.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Comment {
    @Id
    String id;
    @DBRef
    User user;
    @DBRef
    Publication publication;

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
