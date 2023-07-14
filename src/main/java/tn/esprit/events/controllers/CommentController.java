package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.ICommentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController implements AbstractCrudController<CommentDto> {

    private final ICommentService iCommentService;
    @Override
    public CommentDto save(CommentDto commentDto) {
        return iCommentService.save(commentDto);
    }

    @Override
    public List<CommentDto> getAll() {
        return iCommentService.getAll();
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        return iCommentService.update(commentDto);
    }

    @PatchMapping(path = "/{id}")
    public CommentDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iCommentService.updatePatch(fields,id);
    }
}
