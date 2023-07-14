package tn.esprit.events.services;

import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.entities.Topic;
import tn.esprit.events.services.abstracts.ICrudService;

import java.time.LocalDate;
import java.util.List;

public interface IPublicationService extends ICrudService<PublicationDto> {


    PublicationDto changePublicationReacts(ReactDto reactDto, String publicationId);
}
