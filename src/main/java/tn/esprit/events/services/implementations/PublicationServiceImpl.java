package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.entities.Topic;
import tn.esprit.events.repositories.PublicationRepository;
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

    @Override
    public PublicationDto getById(Long id) {
        return PublicationDto.entityToDto(publicationRepository.findById(id).get());
    }


    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication createFeedbackPublication(Publication publication) {
        publication.setTopic(Topic.FEEDBACK);
        return publicationRepository.save(publication);
    }
}
