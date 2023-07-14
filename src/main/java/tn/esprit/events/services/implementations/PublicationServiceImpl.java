package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.PublicationRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IPublicationService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PublicationServiceImpl implements IPublicationService {

    private final PublicationRepository publicationRepository;
    @Override
    public PublicationDto save(PublicationDto publicationDto) {
        return PublicationDto.entityToDto(publicationRepository.save(PublicationDto.dtoToEntity(publicationDto)));
    }

    @Override
    public List<PublicationDto> getAll() {
        return PublicationDto.entitiesToDtos(publicationRepository.findAll());
    }

    @Override
    public PublicationDto update(PublicationDto publicationDto) {
        return PublicationDto.entityToDto(publicationRepository.save(PublicationDto.dtoToEntity(publicationDto)));
    }

    @Override
    public PublicationDto getById(Long id) {
        return PublicationDto.entityToDto(publicationRepository.findById(id).get());
    }

    @Override
    public PublicationDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        Publication publication = publicationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("publication not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("publication not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Publication.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, publication, value);
        });

        return update(PublicationDto.entityToDto(publication));
    }
}
