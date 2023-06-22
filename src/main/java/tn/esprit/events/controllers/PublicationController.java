package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.services.IPublicationService;

import java.util.List;

@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController implements AbstractCrudController<PublicationDto> {

    public PublicationController(IPublicationService publicationService) {
        this.iPublicationService = publicationService;
    }
    private final IPublicationService iPublicationService;
    @Override
    public PublicationDto save(PublicationDto publicationDto) {
        return iPublicationService.save(publicationDto);
    }

    @Override
    public List<PublicationDto> getAll() {
        return iPublicationService.getAll();
    }

    @Override
    public PublicationDto update(PublicationDto publicationDto) {
        return iPublicationService.update(publicationDto);
    }
    @PostMapping("/feedback")
    public ResponseEntity<Publication> createFeedbackPublication(@RequestBody Publication publication) {
        Publication createdPublication = iPublicationService.createFeedbackPublication(publication);
        return ResponseEntity.ok(createdPublication);
    }
}

