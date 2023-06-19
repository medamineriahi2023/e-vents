package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.List;

public interface IEventService extends ICrudService<EventDto> {

    boolean isPresentiel(EventDto eventDto);

    List<EventDto> getEventsPresentiel();
    List<EventDto> getEventsOnline();

    List<EventDto> searchEventsByCategory(Long categoryId);
    List<EventDto> searchEventsByName(String eventName);
    List<EventDto> searchEventsByLocation(String locationName);
    List<EventDto> searchEventsByOrganizer(String organizerId);
}
