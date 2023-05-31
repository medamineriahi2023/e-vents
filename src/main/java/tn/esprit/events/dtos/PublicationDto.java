package tn.esprit.events.dtos;

import lombok.*;
import tn.esprit.events.entities.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PublicationDto {


    private Long id;
    private LocalDate date;
    private  String content;
    private Topic topic;


    //list of ids joined by ","
    private String users;
    private String creatorId;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<React> reacts;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Comment> comments;


    public static PublicationDto entityToDto(Publication publication){
        return PublicationDto.builder().id(publication.getId()).date(publication.getDate()).content(publication.getContent()).topic(publication.getTopic()).users(publication.getUsers()).creatorId(publication.getCreatorId()).reacts(publication.getReacts()).comments(publication.getComments()).build();
    }

    public static Publication dtoToEntity(PublicationDto publicationDto){
        return Publication.builder().id(publicationDto.getId()).date(publicationDto.getDate()).content(publicationDto.getContent()).topic(publicationDto.getTopic()).users(publicationDto.getUsers()).creatorId(publicationDto.getCreatorId()).reacts(publicationDto.getReacts()).comments(publicationDto.getComments()).build();
    }

    public static List<PublicationDto> entitiesToDtos(List<Publication> publications){
        return publications.stream().map(PublicationDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Publication> dtosToEntities(List<PublicationDto> publicationDtos){
        return publicationDtos.stream().map(PublicationDto::dtoToEntity).collect(Collectors.toList());
    }


}
