package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.PublicationDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.IPublicationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("publication")
@RequiredArgsConstructor
public class PublicationController implements AbstractCrudController<PublicationDto> {

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

    @PatchMapping(path = "/{id}")
    public PublicationDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iPublicationService.updatePatch(fields,id);
    }
}
