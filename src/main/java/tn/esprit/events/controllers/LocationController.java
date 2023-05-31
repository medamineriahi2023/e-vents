package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.LocationDto;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.ILocationService;

import java.util.List;

@RestController
@RequestMapping("location")
@RequiredArgsConstructor
public class LocationController implements AbstractController<LocationDto> {

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
}
