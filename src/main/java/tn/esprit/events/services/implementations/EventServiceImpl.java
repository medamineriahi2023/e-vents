package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.entities.Event;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IEventService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;
    @Override
    public EventDto save(EventDto eventDto) {
        return EventDto.entityToDto(eventRepository.save(EventDto.dtoToEntity(eventDto)));
    }

    @Override
    public List<EventDto> getAll() {
        return EventDto.entitiesToDtos(eventRepository.findAll());
    }

    @Override
    public EventDto update(EventDto eventDto) {
        return EventDto.entityToDto(eventRepository.save(EventDto.dtoToEntity(eventDto)));
    }

    @Override
    public EventDto getById(Long id) {
        return EventDto.entityToDto(eventRepository.findById(id).get());

    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public Event createEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(long id, Event event) {
        return null;
    }

    @Override
    public Event getEventById(long id) {

    }

    @Override
    public Event archiveEvent(long id) {
        return null;
    }

    @Override
    public Event rescheduleEvent(long id, Date dateDebut, Date dateFin) {
        return null;
    }

    @Override
    public Event createOnSiteEvent(Event event) {
        Assert.isNull(event, "Event must have details");
        Assert.isNull(event.getName(), "Your event must have a name");
        Assert.hasText(event.getName(), "Your event must have a name");
        Assert.isNull(event.getDateDebutEvent(), "Your event must have a start date");
        Assert.isNull(event.getDateFinEvent(),"Your event must have and end date");
        Assert.isNull(event.getVisibility(), "Is your event public or private? Please choose an option!");
        Assert.isNull(event.getOrganizerId(), "Error loading organizer");
        Assert.isNull(event.getLocation(),"Please set the location for your event");
        event.setParticipants("");
        event.setStaffs("");
        event.setArchived(false);
        return event;
    }
}
