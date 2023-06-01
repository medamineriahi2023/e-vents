package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.EventRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IEventService;

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
}
