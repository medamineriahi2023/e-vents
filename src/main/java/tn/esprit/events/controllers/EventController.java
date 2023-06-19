package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.EventDto;
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

    /*@Override
    @GetMapping("/{id}")
    public EventDto getById(@PathVariable Long id) {
        return iEventService.getById(id);
    }*/

    @GetMapping("/presentiel")
    public List<EventDto> getPresentielEvents() {
        return iEventService.getEventsPresentiel();
    }

    @GetMapping("/online")
    public List<EventDto> getOnlineEvents() {
        return iEventService.getEventsOnline();
    }

    @GetMapping("/category/{categoryId}")
    public List<EventDto> searchEventsByCategory(@PathVariable Long categoryId) {
        return iEventService.searchEventsByCategory(categoryId);
    }

    @GetMapping("/name/{eventName}")
    public List<EventDto> searchEventsByName(@PathVariable String eventName) {
        return iEventService.searchEventsByName(eventName);
    }

    @GetMapping("/location/{locationName}")
    public List<EventDto> searchEventsByLocation(@PathVariable String locationName) {
        return iEventService.searchEventsByLocation(locationName);
    }

    @GetMapping("/organizer/{organizerId}")
    public List<EventDto> searchEventsByOrganizer(@PathVariable String organizerId) {
        return iEventService.searchEventsByOrganizer(organizerId);
    }
}
