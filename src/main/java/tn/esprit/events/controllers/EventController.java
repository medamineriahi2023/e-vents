package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.IEventService;

import java.util.List;
import java.util.Map;

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

    @PatchMapping(path = "/{id}")
    public EventDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iEventService.updatePatch(fields,id);
    }
}
