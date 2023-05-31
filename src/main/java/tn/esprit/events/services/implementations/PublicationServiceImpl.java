package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.PublicationRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IPublicationService;

import java.util.List;

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
}
