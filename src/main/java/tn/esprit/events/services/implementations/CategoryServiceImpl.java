package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CategotyRepository;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.services.ICategoryService;
import tn.esprit.events.services.ICommentService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @Override
    public CategoryDto getById(Long id) {
        return CategoryDto.entityToDto(categotyRepository.findById(id).get());
    }

    @Override
    public CategoryDto updatePatch(Map<Object, Object> fields , Long id) throws EntityNotFoundException {

        Category category = categotyRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("category not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("category not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Category.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, category, value);
        });

        return update(CategoryDto.entityToDto(category));
    }
}
