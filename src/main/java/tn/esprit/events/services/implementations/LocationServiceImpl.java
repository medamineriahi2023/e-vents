package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.LocationDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Location;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.LocationRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.ILocationService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements ILocationService {

    private final LocationRepository locationRepository;
    @Override
    public LocationDto save(LocationDto locationDto) {
        return LocationDto.entityToDto(locationRepository.save(LocationDto.dtoToEntity(locationDto)));
    }

    @Override
    public List<LocationDto> getAll() {
        return LocationDto.entitiesToDtos(locationRepository.findAll());
    }

    @Override
    public LocationDto update(LocationDto locationDto) {
        return LocationDto.entityToDto(locationRepository.save(LocationDto.dtoToEntity(locationDto)));
    }

    @Override
    public LocationDto getById(Long id) {
        return LocationDto.entityToDto(locationRepository.findById(id).get());
    }

    @Override
    public LocationDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        Location location = locationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("location not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("location not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Location.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, location, value);
        });

        return update(LocationDto.entityToDto(location));
    }
}
