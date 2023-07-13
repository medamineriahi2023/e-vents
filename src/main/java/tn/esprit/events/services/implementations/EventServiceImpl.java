package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.Role;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.entities.Event;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.IEventService;
import tn.esprit.events.utils.UserKcService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {

    private final EventRepository eventRepository;

    private final UserServiceImpl userServiceImpl;

    @Override
    public EventDto save(EventDto eventDto) {
        Assert.hasText(eventDto.getName(), "Your event must have a name");
        Assert.hasText(eventDto.getOrganizer().getId(), "Error loading organizer");
        eventDto.setArchived(false);
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
    @Transactional
    public EventDto archiveEvent(String eventId) {
        Event eventToUpdate = eventRepository.findById(Long.parseLong(eventId)).get();
        eventToUpdate.setArchived(true);
        EventDto updatedEventDto = EventDto.entityToDto(eventToUpdate);
        return updatedEventDto;
    }

    @Override
    @Transactional
    public EventDto rescheduleEvent(EventDto eventDtoToUpdate){
        Event updatedEvent = eventRepository.findById(eventDtoToUpdate.getId()).get();
        updatedEvent.setDateDebutEvent(eventDtoToUpdate.getDateDebutEvent());
        updatedEvent.setDateFinEvent(eventDtoToUpdate.getDateFinEvent());
        EventDto updatedEventDto = EventDto.entityToDto(updatedEvent);
        return updatedEventDto;
    }

    @Override
    @Transactional
    public EventDto addParticipant(UserDto userDto, String eventId) {
        EventDto eventDtoToUpdate = EventDto.entityToDto(eventRepository.findById(Long.parseLong(eventId)).get());
        if(eventDtoToUpdate.getParticipants() == null){
            eventDtoToUpdate.setParticipants(new ArrayList<UserDto>());
        }
        if(eventDtoToUpdate.getParticipants().stream().filter(participant -> participant.getId().equals(userDto.getId())).findFirst().isEmpty()){
            eventDtoToUpdate.getParticipants().add(userDto);
        }
        Event eventToUpdate = EventDto.dtoToEntity(eventDtoToUpdate);
        Event updatedEvent = eventRepository.findById(Long.parseLong(eventId)).get();
        updatedEvent.setParticipants(eventToUpdate.getParticipants());
        EventDto updatedEventDto = EventDto.entityToDto(updatedEvent);
        return updatedEventDto;
    }

    @Override
    @Transactional
    public EventDto addStaff(UserDto userDto, String eventId) {
        EventDto eventDtoToUpdate = EventDto.entityToDto(eventRepository.findById(Long.parseLong(eventId)).get());
        if(eventDtoToUpdate.getStaffs() == null){
            eventDtoToUpdate.setStaffs(new ArrayList<UserDto>());
        }

        eventDtoToUpdate.getStaffs().add(userDto);
        Event eventToUpdate = EventDto.dtoToEntity(eventDtoToUpdate);
        Event updatedEvent = eventRepository.findById(Long.parseLong(eventId)).get();
        updatedEvent.setStaffs(eventToUpdate.getStaffs());
        EventDto updatedEventDto = EventDto.entityToDto(updatedEvent);
        return updatedEventDto;
    }

    @Override
    public List<EventDto> identifyCloseEvent(String location) {
        List<Event> events = this.eventRepository.findEventsByLocationGovName(location);
        return EventDto.entitiesToDtos(events);
    }

    @Override
    public Boolean canAddStaff(EventDto eventDto, UserDto userDto) {
        return eventDto.getStaffs().stream().filter(staff -> staff.getId().equals(userDto.getId())).findFirst().isEmpty() && userDto.getId() != eventDto.getOrganizer().getId();
    }

    @Override
    public Boolean canAddParticipant(EventDto eventDto, UserDto userDto) {
        return eventDto.getParticipants().stream().filter(participant -> participant.getId().equals(userDto.getId())).findFirst().isEmpty() && userDto.getId() != eventDto.getOrganizer().getId();
    }

    @Override
    public Boolean eventDoesExist(Long eventId) {
        return eventRepository.findById(eventId).get() != null;
    }

    @Override
    public List<EventDto> getEventsOfOrganizer(String organizerId) {
        return EventDto.entitiesToDtos(eventRepository.findByOrganizerId(organizerId));
        //UserDto organizer = UserKcService.findById(organizerId);
        //Role role = organizer.getRoles().stream().filter(r -> r.getName().equals("organizer")).findFirst().get();
        //if(role != null){
        //    return EventDto.entitiesToDtos(eventRepository.findByOrganizerId(organizerId));
        //}
        //return null;
    }


}
