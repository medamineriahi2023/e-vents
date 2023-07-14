package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.services.ICommentService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    @Override
    public CommentDto save(CommentDto commentDto) {
        return CommentDto.entityToDto(commentRepository.save(CommentDto.dtoToEntity(commentDto)));
    }

    @Override
    public List<CommentDto> getAll() {
        return CommentDto.entitiesToDtos(commentRepository.findAll());
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        return CommentDto.entityToDto(commentRepository.save(CommentDto.dtoToEntity(commentDto)));
    }

    @Override
    public CommentDto getById(Long id) {
        return CommentDto.entityToDto(commentRepository.findById(id).get());

    }

    @Override
    public CommentDto updatePatch(Map<Object, Object> fields , Long id) throws EntityNotFoundException {

        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("comment not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("comment not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Comment.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, comment, value);
        });

        return update(CommentDto.entityToDto(comment));
    }
}
