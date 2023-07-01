package tn.esprit.events.dtos;

import lombok.*;
import tn.esprit.events.entities.*;
import tn.esprit.events.utils.UserKcService;

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
    private List<UserDto> users;
    private UserDto creator;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<ReactDto> reacts;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<CommentDto> comments;


    public static PublicationDto entityToDto(Publication publication){
        return PublicationDto.builder().id(publication.getId()).date(publication.getDate()).
                content(publication.getContent()).topic(publication.getTopic()).
                users(publication.getUsers() != null ? UserKcService.splitAndReturn(publication.getUsers()) : null ).
                creator(UserKcService.findById(publication.getCreatorId())).
                reacts(publication.getReacts() != null ? ReactDto.entitiesToDtos(publication.getReacts()) : null).
                comments(publication.getComments() != null ? CommentDto.entitiesToDtos(publication.getComments()) : null)
                .build();
    }

    public static Publication dtoToEntity(PublicationDto publicationDto){
        return Publication.builder().id(publicationDto.getId()).date(publicationDto.getDate()).
                content(publicationDto.getContent()).topic(publicationDto.getTopic()).
                users(publicationDto.getUsers() != null ? publicationDto.getUsers().stream().map(UserDto::getId).collect(Collectors.joining(",")): null).
                creatorId(publicationDto.creator.getId()).
                reacts(publicationDto.getReacts() != null ? ReactDto.dtosToEntities(publicationDto.getReacts()) : null ).
                comments(publicationDto.getComments()!= null ? CommentDto.dtosToEntities(publicationDto.getComments()): null ).build();
    }

    public static List<PublicationDto> entitiesToDtos(List<Publication> publications){
        return publications.stream().map(PublicationDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Publication> dtosToEntities(List<PublicationDto> publicationDtos){
        return publicationDtos.stream().map(PublicationDto::dtoToEntity).collect(Collectors.toList());
    }


}
