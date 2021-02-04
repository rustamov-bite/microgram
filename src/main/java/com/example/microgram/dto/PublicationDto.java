package com.example.microgram.dto;

import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDto {

    public static PublicationDto getPublicationDto(Publication publication) {
        return PublicationDto.builder()
                .user(publication.getUser())
                .imageId(publication.getImageId())
                .description(publication.getDescription())
                .publicationDate(publication.getPublicationDate())
                .build();
    }

    public static List<PublicationDto> getListOfPublicationDto(List<Publication> publications) {
        List<PublicationDto> publicationDtos = new ArrayList<>();
        for (Publication p : publications) {
            publicationDtos.add(PublicationDto.getPublicationDto(p));
        }
        return publicationDtos;
    }

    private final User user;
    private final String imageId;
    private final String description;
    private final LocalDate publicationDate;
}
