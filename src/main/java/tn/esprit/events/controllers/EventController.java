package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.services.IEventService;
import tn.esprit.events.services.IPublicationService;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController implements AbstractCrudController<EventDto> {

    private final IEventService iEventService;
    private final IPublicationService iPublicationService;

    @Override
    public EventDto save(EventDto eventDto) {
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

    @PostMapping("/{eventId}/publication/feedback")
    public ResponseEntity<EventDto> createFeedbackPublication(@RequestBody PublicationDto publicationDto, @PathVariable String eventId) {
        EventDto savedEvent = iEventService.addFeedbackPublication(publicationDto, eventId);
        return new ResponseEntity<>(savedEvent, HttpStatus.OK);
    }
}