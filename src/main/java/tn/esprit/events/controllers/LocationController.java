package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.LocationDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.ILocationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("location")
@RequiredArgsConstructor
public class LocationController implements AbstractCrudController<LocationDto> {

    private final ILocationService iLocationService;
    @Override
    public LocationDto save(LocationDto locationDto) {
        return iLocationService.save(locationDto);
    }

    @Override
    public List<LocationDto> getAll() {
        return iLocationService.getAll();
    }

    @Override
    public LocationDto update(LocationDto locationDto) {
        return iLocationService.update(locationDto);
    }

    @PatchMapping(path = "/{id}")
    public LocationDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iLocationService.updatePatch(fields,id);
    }
}
