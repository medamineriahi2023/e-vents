package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.LocationDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.LocationRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.ILocationService;

import java.util.List;

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
}
