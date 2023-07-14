package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.repositories.CategotyRepository;
import tn.esprit.events.services.ICategoryService;

import java.lang.reflect.Field;
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
    public CategoryDto updatePatch(Map<Object, Object> fields) {

        Category category = categotyRepository.findById(Long.parseLong(fields.get("id").toString())).
                orElseThrow(() -> new IllegalArgumentException("category not found"));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Category.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, category, value);
        });

        return CategoryDto.entityToDto(category);
    }
}
