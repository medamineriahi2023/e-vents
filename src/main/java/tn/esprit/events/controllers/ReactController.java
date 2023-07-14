package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.IReactService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("react")
@RequiredArgsConstructor
public class ReactController implements AbstractCrudController<ReactDto> {

    private final IReactService iReactService;
    @Override
    public ReactDto save(ReactDto reactDto) {
        return iReactService.save(reactDto);
    }

    @Override
    public List<ReactDto> getAll() {
        return iReactService.getAll();
    }

    @Override
    public ReactDto update(ReactDto reactDto) {
        return iReactService.update(reactDto);
    }

    @PatchMapping(path = "/{id}")
    public ReactDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iReactService.updatePatch(fields,id);
    }
}
