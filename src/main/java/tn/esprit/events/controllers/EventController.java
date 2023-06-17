package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.entities.Event;
import tn.esprit.events.services.IEventService;

import java.util.List;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController implements AbstractCrudController<EventDto> {

    private final IEventService iEventService;
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

    @PostMapping("/on-site")
    public ResponseEntity<EventDto> createOnSiteEvent(@RequestBody EventDto eventDto){
        Event eventRequest = EventDto.dtoToEntity(eventDto);
        Event event = iEventService.createOnSiteEvent(eventRequest);
        EventDto responseEventDto = EventDto.entityToDto(event);
        return new ResponseEntity<EventDto>(responseEventDto, HttpStatus.CREATED);
    }

    @Put
}
