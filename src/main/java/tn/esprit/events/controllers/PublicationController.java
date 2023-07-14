package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IPublicationService;

import java.util.List;



@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController implements AbstractCrudController<PublicationDto> {

    private final IPublicationService iPublicationService;
    private final ICommentService iCommentService;
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



    @PostMapping("/{publicationId}/comments")
    public ResponseEntity<PublicationDto> addCommentToPublication(
            @PathVariable("publicationId") Long publicationId,
            @RequestBody CommentDto commentDto) {
        PublicationDto publicationDto = iPublicationService.getById(publicationId);
        if (publicationDto == null) {
            return ResponseEntity.notFound().build();
        }

        CommentDto savedComment = iCommentService.save(commentDto);
        publicationDto.getComments().add(savedComment);
        PublicationDto updatedPublication = iPublicationService.update(publicationDto);

        return ResponseEntity.ok(updatedPublication);
    }


    @PutMapping("/{publicationId}/reacts")
    public PublicationDto addReactToPublication (@RequestBody ReactDto reactDto , @PathVariable("publicationId") String  publicationId){
        return iPublicationService.changePublicationReacts(reactDto,publicationId);
    }

}



