package tn.esprit.events.services;

import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.Map;

public interface ICategoryService extends ICrudService<CategoryDto> {


    CategoryDto updatePatch(Map<Object, Object> fields);
}
