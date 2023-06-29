package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.services.ICategoryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor

public class CategoryController implements AbstractCrudController<CategoryDto> {

    private final ICategoryService iCategoryService;
    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return iCategoryService.save(categoryDto);
    }

    @Override
    public List<CategoryDto> getAll() {
        return iCategoryService.getAll();
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return iCategoryService.update(categoryDto);
    }

    @PatchMapping
    public CategoryDto updatePatch(@RequestBody Map<Object,Object> fields){
        return iCategoryService.updatePatch(fields);
    }
}
