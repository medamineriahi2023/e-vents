package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CommentDto;
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
    @PostMapping("/{publicationId}/comments")
    public ResponseEntity<PublicationDto> addCommentToPublication(
            @PathVariable String publicationId,
            @RequestBody CommentDto commentDto) {
        PublicationDto updatedPublication = iPublicationService.addCommentToPublication(commentDto, publicationId);
        return ResponseEntity.ok(updatedPublication);
    }
}

