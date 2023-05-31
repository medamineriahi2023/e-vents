package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.*;
import tn.esprit.events.userUtils.UserKcService;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EventDto {


    private Long id;
    private String name;
    private LocalDate dateDebutEvent;
    private LocalDate dateFinEvent;
    private Type type;
    private Visibility visibility;
    private CategoryDto category;


    private UserDto organizer;

    //Its a list joined by ,
    private List<UserDto> staffs;
    //Its a list joined by ,
    private List<UserDto> participants;


    private String backGroundImage;
    private String eventImage;
    private String video;
    private boolean archived;
    private String description;
    private LocationDto location ;
    private String locationName;
    private String rue;
    private  String zipCode;


    public static EventDto entityToDto(Event event){
        return EventDto.builder().id(event.getId()).name(event.getName()).
                dateDebutEvent(event.getDateDebutEvent()).dateFinEvent(event.getDateFinEvent()).
                type(event.getType()).visibility(event.getVisibility()).
                category(CategoryDto.entityToDto(event.getCategory())).organizer(UserKcService.findById(event.getOrganizerId())).
                staffs(UserKcService.splitAndReturn(event.getStaffs())).
                participants(UserKcService.splitAndReturn(event.getParticipants())).backGroundImage(event.getBackGroundImage()).eventImage(event.getEventImage()).
                video(event.getVideo()).archived(event.isArchived()).description(event.getDescription()).location(LocationDto.entityToDto(event.getLocation())).
                locationName(event.getLocationName()).rue(event.getRue()).zipCode(event.getZipCode()).build();
    }

    public static Event dtoToEntity(EventDto eventDto){
        return Event.builder().id(eventDto.getId()).name(eventDto.getName()).dateDebutEvent(eventDto.getDateDebutEvent()).
                dateFinEvent(eventDto.getDateFinEvent()).type(eventDto.getType()).visibility(eventDto.getVisibility()).
                category(CategoryDto.dtoToEntity(eventDto.getCategory())).organizerId(eventDto.getOrganizer().getId()).
                staffs(eventDto.getStaffs().stream().map(UserDto::getId).collect(Collectors.joining(","))).
                participants(eventDto.getParticipants().stream().map(UserDto::getId).collect(Collectors.joining(","))).
                backGroundImage(eventDto.getBackGroundImage()).eventImage(eventDto.getEventImage()).
                video(eventDto.getVideo()).archived(eventDto.isArchived()).description(eventDto.getDescription()).
                location(LocationDto.dtoToEntity(eventDto.getLocation())).locationName(eventDto.getLocationName()).rue(eventDto.getRue()).
                zipCode(eventDto.getZipCode()).build();
    }

    public static List<EventDto> entitiesToDtos(List<Event> events){
        return events.stream().map(EventDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Event> dtosToEntities(List<EventDto> eventDtos){
        return eventDtos.stream().map(EventDto::dtoToEntity).collect(Collectors.toList());
    }


}
