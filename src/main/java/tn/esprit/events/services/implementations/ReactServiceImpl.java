package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.ReactRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IReactService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactServiceImpl implements IReactService {

    private final ReactRepository reactRepository;
    @Override
    public ReactDto save(ReactDto reactDto) {
        return ReactDto.entityToDto(reactRepository.save(ReactDto.dtoToEntity(reactDto)));
    }

    @Override
    public List<ReactDto> getAll() {
        return ReactDto.entitiesToDtos(reactRepository.findAll());
    }

    @Override
    public ReactDto update(ReactDto reactDto) {
        return ReactDto.entityToDto(reactRepository.save(ReactDto.dtoToEntity(reactDto)));
    }
}
