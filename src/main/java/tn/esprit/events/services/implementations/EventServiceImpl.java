package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.entities.Event;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.IEventService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;
    private final UserDto userDto;
    @Override
    public EventDto save(EventDto eventDto) {
        Assert.isNull(eventDto, "Event must have details");
        Assert.isNull(eventDto.getName(), "Your event must have a name");
        Assert.hasText(eventDto.getName(), "Your event must have a name");
        Assert.isNull(eventDto.getDateDebutEvent(), "Your event must have a start date");
        Assert.isNull(eventDto.getDateFinEvent(),"Your event must have and end date");
        Assert.isNull(eventDto.getVisibility(), "Is your event public or private? Please choose an option!");
        Assert.isNull(eventDto.getOrganizer().getId(), "Error loading organizer");
        Assert.isNull(eventDto.getLocation(),"Please set the location for your event");
        eventDto.setParticipants(new ArrayList<UserDto>());
        eventDto.setStaffs(new ArrayList<UserDto>());
        eventDto.setArchived(false);
        return EventDto.entityToDto(eventRepository.save(EventDto.dtoToEntity(eventDto)));
    }

    @Override
    public List<EventDto> getAll() {
        return EventDto.entitiesToDtos(eventRepository.findAll());
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Assert.isNull(eventDto, "Event is null");
        Assert.isNull(eventDto.getName(), "Event name is null");
        return EventDto.entityToDto(eventRepository.save(EventDto.dtoToEntity(eventDto)));
    }

    @Override
    public EventDto getById(Long id) {
        return EventDto.entityToDto(eventRepository.findById(id).get());
    }

    @Override
    @Transactional
    public EventDto archiveEvent(String eventId) {
        EventDto eventDto = this.getById(Long.parseLong(eventId));
        eventDto.setArchived(true);
        return eventDto;
    }

    @Override
    @Transactional
    public EventDto rescheduleEvent(EventDto eventDto){
        EventDto updated = this.getById(eventDto.getId());

        return eventDto;
    }

    @Override
    @Transactional
    public EventDto addParticipant(UserDto userDto, String eventId) {
        EventDto eventDto = this.getById(Long.parseLong(eventId));
        eventDto.getParticipants().add(userDto);
        return eventDto;
    }

    @Override
    public EventDto addStaff(UserDto userDto, String eventId) {
        EventDto eventDto = this.getById(Long.parseLong(eventId));
        eventDto.getStaffs().add(userDto);
        return eventDto;
    }

    @Override
    public List<EventDto> identifyCloseEvent(String location) {
        List<Event> events = this.eventRepository.findEventsByLocationGovName(location);
        return EventDto.entitiesToDtos(events);
    }
}
