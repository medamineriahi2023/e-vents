package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.services.ICategoryService;
import tn.esprit.events.services.ICommentService;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController implements AbstractController<CategoryDto> {

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
}
