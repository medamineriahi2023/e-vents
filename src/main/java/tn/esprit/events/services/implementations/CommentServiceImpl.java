package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.services.ICommentService;

import java.util.List;
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
}
