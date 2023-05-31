package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.repositories.CategotyRepository;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.services.ICategoryService;
import tn.esprit.events.services.ICommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final CategotyRepository categotyRepository;
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return CategoryDto.entityToDto(categotyRepository.save(CategoryDto.dtoToEntity(categoryDto)));
    }

    @Override
    public List<CategoryDto> getAll() {
        return CategoryDto.entitiesToDtos(categotyRepository.findAll());
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return CategoryDto.entityToDto(categotyRepository.save(CategoryDto.dtoToEntity(categoryDto)));
    }
}
