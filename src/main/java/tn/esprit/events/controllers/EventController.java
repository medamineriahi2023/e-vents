package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.IEventService;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController implements AbstractCrudController<EventDto> {


    private final IEventService iEventService;
    private final EventRepository eventRepository;

    @Override
    public EventDto save(@RequestBody EventDto eventDto) {
        return iEventService.save(eventDto);
    }

    @Override
    public List<EventDto> getAll() {
        return iEventService.getAll();
    }

    @Override
    public EventDto update(EventDto eventDto) {
        return iEventService.update(eventDto);
    }

    @GetMapping("{eventId}")
    public EventDto get(@PathVariable("eventId") String eventId){
        return EventDto.entityToDto(eventRepository.findById(Long.parseLong(eventId)).get());
    }

    @PutMapping("archive/{eventId}")
    public EventDto archiveEvent(@PathVariable("eventId") String eventId){
        return iEventService.archiveEvent(eventId);
    }
    @PutMapping("reschedule/")
    public EventDto rescheduleEvent(@RequestBody EventDto eventDto){
        return iEventService.rescheduleEvent(eventDto);
    }

    @PostMapping("{eventId}/add/participant")
    EventDto addParticipant(UserDto userDto, String eventId){
        return iEventService.addParticipant(userDto,eventId);
    }
    @PostMapping("{eventId}/add/staff")
    ResponseEntity<?> addStaff(@RequestBody UserDto userDto,@PathVariable("eventId") String eventId) {
        if(!iEventService.eventDoesExist(Long.parseLong(eventId))){
            return new ResponseEntity<>("Event doesn't exists", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(iEventService.addStaff(userDto, eventId),HttpStatus.OK);
    }

    @GetMapping("close-to/{userId}")
    List<EventDto> identifyCloseEvent(@PathVariable("userId")String userId){
        return iEventService.identifyCloseEvent(userId);
    }
}
