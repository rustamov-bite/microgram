package com.example.microgram.dto;

import com.example.microgram.entities.PubImage;
import com.example.microgram.entities.Publication;
import com.example.microgram.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
public class PublicationDto {
    private final User user;
    private final PubImage image;
    private final String description;
    private final LocalDate publicationDate;

    public static PublicationDto getPublicationDto(Publication publication) {
        return PublicationDto.builder()
                .user(publication.getUser())
                .image(publication.getImage())
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
}
