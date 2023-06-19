package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.entities.React;
import tn.esprit.events.repositories.PublicationRepository;
import tn.esprit.events.services.IPublicationService;

import javax.transaction.Transactional;
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
    @Transactional
    @Override
    public PublicationDto changePublicationReacts(ReactDto reactDto, String publicationId ) {
        PublicationDto publicationDto = this.getById(Long.parseLong(publicationId));
        React react = publicationDto.getReacts().stream().filter(r -> r.getUserId().equals(reactDto.getUser().getId())).findFirst().orElse(null);
        if (react != null) {
            react.setLiked(reactDto.isLiked());
        } else {
            React newReact = new React();
            newReact.setLiked(reactDto.isLiked());
            newReact.setUserId(reactDto.getUser().getId());
        }
        return null;
    }
}
