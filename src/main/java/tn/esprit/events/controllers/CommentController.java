package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.services.ICommentService;

import java.util.List;
@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController implements AbstractController<CommentDto> {

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
}
