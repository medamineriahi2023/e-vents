package tn.esprit.events.services;

import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.services.abstracts.ICrudService;

public interface IPublicationService extends ICrudService<PublicationDto> {
    PublicationDto changePublicationReacts(ReactDto reactDto, String publicationId);
}
