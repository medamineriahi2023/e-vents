package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.services.abstracts.ICrudService;

public interface IPublicationService extends ICrudService<PublicationDto> {


     PublicationDto createFeedbackPublication(PublicationDto publicationDto);

}
