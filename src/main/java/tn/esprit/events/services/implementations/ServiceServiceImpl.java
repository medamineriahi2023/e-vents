package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.ServiceDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.ServiceRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IServiceService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements IServiceService {

    private final ServiceRepository serviceRepository;
    @Override
    public ServiceDto save(ServiceDto serviceDto) {
        return ServiceDto.entityToDto(serviceRepository.save(ServiceDto.dtoToEntity(serviceDto)));
    }

    @Override
    public List<ServiceDto> getAll() {
        return ServiceDto.entitiesToDtos(serviceRepository.findAll());
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        return ServiceDto.entityToDto(serviceRepository.save(ServiceDto.dtoToEntity(serviceDto)));
    }

    @Override
    public ServiceDto getById(Long id) {
        return ServiceDto.entityToDto(serviceRepository.findById(id).get());

    }

    @Override
    public ServiceDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        tn.esprit.events.entities.Service service = serviceRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("service not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("service not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(tn.esprit.events.entities.Service.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, service, value);
        });

        return update(ServiceDto.entityToDto(service));
    }
}
