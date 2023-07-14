package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Event;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IEventService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public EventDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        Event event = eventRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("event not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("event not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Event.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, event, value);
        });

        return update(EventDto.entityToDto(event));
    }

}
