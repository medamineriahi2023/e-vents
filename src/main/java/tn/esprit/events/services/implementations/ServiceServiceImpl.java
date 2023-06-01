package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.ServiceDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.ServiceRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IServiceService;

import java.util.List;

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
}
