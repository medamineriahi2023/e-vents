package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IReactService;

import java.util.List;

@RestController
@RequestMapping("react")
@RequiredArgsConstructor
public class ReactController implements AbstractController<ReactDto> {

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
}
