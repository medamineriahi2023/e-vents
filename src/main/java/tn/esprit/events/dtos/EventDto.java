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

public class EventDto {


    private Long id;
    private String name;
    private LocalDate dateDebutEvent;
    private LocalDate dateFinEvent;
    private Type type;
    private Visibility visibility;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    private String organizerId;

    //Its a list joined by ,
    private String staffs;
    //Its a list joined by ,
    private String participants;


    private String backGroundImage;
    private String eventImage;
    private String video;
    private boolean archived;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location ;
    private String locationName;
    private String rue;
    private  String zipCode;


    public static EventDto entityToDto(Event event){
        return EventDto.builder().id(event.getId()).name(event.getName()).dateDebutEvent(event.getDateDebutEvent()).dateFinEvent(event.getDateFinEvent()).type(event.getType()).visibility(event.getVisibility()).category(event.getCategory()).organizerId(event.getOrganizerId()).staffs(event.getStaffs()).participants(event.getParticipants()).backGroundImage(event.getBackGroundImage()).eventImage(event.getEventImage()).video(event.getVideo()).archived(event.isArchived()).description(event.getDescription()).location(event.getLocation()).locationName(event.getLocationName()).rue(event.getRue()).zipCode(event.getZipCode()).build();
    }

    public static Event dtoToEntity(EventDto eventDto){
        return Event.builder().id(eventDto.getId()).name(eventDto.getName()).dateDebutEvent(eventDto.getDateDebutEvent()).dateFinEvent(eventDto.getDateFinEvent()).type(eventDto.getType()).visibility(eventDto.getVisibility()).category(eventDto.getCategory()).organizerId(eventDto.getOrganizerId()).staffs(eventDto.getStaffs()).participants(eventDto.getParticipants()).backGroundImage(eventDto.getBackGroundImage()).eventImage(eventDto.getEventImage()).video(eventDto.getVideo()).archived(eventDto.isArchived()).description(eventDto.getDescription()).location(eventDto.getLocation()).locationName(eventDto.getLocationName()).rue(eventDto.getRue()).zipCode(eventDto.getZipCode()).build();
    }

    public static List<EventDto> entitiesToDtos(List<Event> events){
        return events.stream().map(EventDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Event> dtosToEntities(List<EventDto> eventDtos){
        return eventDtos.stream().map(EventDto::dtoToEntity).collect(Collectors.toList());
    }


}
