package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.entities.Topic;
import tn.esprit.events.repositories.PublicationRepository;
import tn.esprit.events.services.IPublicationService;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
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

    @Override
    public PublicationDto getById(Long id) {
        return PublicationDto.entityToDto(publicationRepository.findById(id).get());
    }

    @Override
    @Transactional
    public PublicationDto createFeedbackPublication(PublicationDto publicationDto) {
        publicationDto.setTopic(Topic.FEEDBACK);
        Publication publication = PublicationDto.dtoToEntity(publicationDto);
        Publication savedPublication = this.publicationRepository.save(publication);
        PublicationDto savedPublicationDto = PublicationDto.entityToDto(savedPublication);
        return savedPublicationDto;
    }
}
