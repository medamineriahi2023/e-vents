package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.React;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.ReactRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IReactService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReactServiceImpl implements IReactService {

    private final ReactRepository reactRepository;
    @Override
    public ReactDto save(ReactDto reactDto) {
        return ReactDto.entityToDto(reactRepository.save(ReactDto.dtoToEntity(reactDto)));
    }

    @Override
    public List<ReactDto> getAll() {
        return ReactDto.entitiesToDtos(reactRepository.findAll());
    }

    @Override
    public ReactDto update(ReactDto reactDto) {
        return ReactDto.entityToDto(reactRepository.save(ReactDto.dtoToEntity(reactDto)));
    }

    @Override
    public ReactDto getById(Long id) {
        return ReactDto.entityToDto(reactRepository.findById(id).get());

    }

    @Override
    public ReactDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        React react = reactRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("react not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("react not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(React.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, react, value);
        });

        return update(ReactDto.entityToDto(react));
    }
}
