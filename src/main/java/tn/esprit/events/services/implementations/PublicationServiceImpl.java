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


    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication createFeedbackPublication(Publication publication) {
        publication.setTopic(Topic.FEEDBACK);
        return publicationRepository.save(publication);
    }
    @Override
    public PublicationDto addCommentToPublication(CommentDto commentDto, String publicationId) {
        Publication publication = publicationRepository.findById(Long.parseLong(publicationId))
                .orElseThrow(() -> new NotFoundException("Publication not found"));

        Comment newComment = new Comment();
        newComment.setContent(commentDto.getContent());
        publication.getComments().add(newComment);

        publication = publicationRepository.save(publication);

        return mapPublicationToDto(publication);
    }
    private PublicationDto mapPublicationToDto(Publication publication) {
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setId(publication.getId());
        publicationDto.setDate(publication.getDate());
        publicationDto.setContent(publication.getContent());
        publicationDto.setTopic(publication.getTopic());
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : publication.getComments()) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setContent(comment.getContent());
            commentDtos.add(commentDto);
        }

        return publicationDto;
    }


}
