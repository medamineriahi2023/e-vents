package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.*;
import tn.esprit.events.utils.UserKcService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    private List<PublicationDto> publications;


    public static EventDto entityToDto(Event event){

        return EventDto.builder().id(event.getId()).
                name(event.getName()).
                dateDebutEvent(event.getDateDebutEvent()).dateFinEvent(event.getDateFinEvent()).
                type(event.getType()).visibility(event.getVisibility()).
                category(CategoryDto.entityToDto(event.getCategory())).
                organizer(UserKcService.findById(event.getOrganizerId())).
                archived(event.isArchived()).
                staffs(event.getStaffs() != null ? UserKcService.splitAndReturn(event.getStaffs()): null).
                participants(event.getParticipants() != null ? UserKcService.splitAndReturn(event.getParticipants()): null).
                backGroundImage(event.getBackGroundImage() != null ? event.getBackGroundImage() : null).
                eventImage(event.getEventImage() != null ? event.getEventImage() : null).
                video(event.getVideo() != null ? event.getVideo() : null ).
                description(event.getDescription() != null ? event.getDescription(): null).
                location(LocationDto.entityToDto(event.getLocation())).
                locationName(event.getLocationName() != null ? event.getLocationName() : null).
                rue(event.getRue() != null ? event.getRue() : null).
                zipCode(event.getZipCode() != null ? event.getZipCode() : null).build();
    }

    public static Event dtoToEntity(EventDto eventDto){
        return Event.builder().id(eventDto.getId()).
                name(eventDto.getName()).
                dateDebutEvent(eventDto.getDateDebutEvent()).dateFinEvent(eventDto.getDateFinEvent()).
                type(eventDto.getType()).
                visibility(eventDto.getVisibility()).
                category(CategoryDto.dtoToEntity(eventDto.getCategory())).
                organizerId(eventDto.getOrganizer().getId()).
                archived(eventDto.isArchived()).
                staffs(eventDto.getStaffs() != null ? eventDto.getStaffs().stream().map(UserDto::getId).collect(Collectors.joining(",")) : null).
                participants(eventDto.getParticipants() != null ? eventDto.getParticipants().stream().map(UserDto::getId).collect(Collectors.joining(",")) : null).
                backGroundImage(eventDto.getBackGroundImage() != null ? eventDto.getBackGroundImage() : null).
                eventImage(eventDto.getEventImage() != null ? eventDto.getEventImage() : null).
                video(eventDto.getVideo() != null ? eventDto.getVideo() : null).
                description(eventDto.getDescription() != null ? eventDto.getDescription() : null).
                location(LocationDto.dtoToEntity(eventDto.getLocation())).
                locationName(eventDto.getLocationName() != null ? eventDto.getLocationName() : null).
                rue(eventDto.getRue() != null ? eventDto.getRue() : null ).
                zipCode(eventDto.getZipCode() != null ? eventDto.getZipCode() : null).build();
    }

    public static List<EventDto> entitiesToDtos(List<Event> events){
        return events.stream().map(EventDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Event> dtosToEntities(List<EventDto> eventDtos){
        return eventDtos.stream().map(EventDto::dtoToEntity).collect(Collectors.toList());
    }


}
