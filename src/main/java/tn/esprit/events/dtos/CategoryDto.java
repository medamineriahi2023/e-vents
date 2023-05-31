package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.userUtils.UserKcService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {


    private Long id;
    private String name;
    private boolean archived;
    private  String icon;

    public static CategoryDto entityToDto(Category category){
        return CategoryDto.builder().name(category.getName()).id(category.
                getId()).icon(category.getIcon()).archived(category.isArchived()).build();
    }

    public static Category dtoToEntity(CategoryDto categoryDto){
        return Category.builder().name(categoryDto.getName()).id(categoryDto.getId()).
                icon(categoryDto.getIcon()).archived(categoryDto.isArchived()).build();
    }

    public static List<CategoryDto> entitiesToDtos(List<Category> categories){
        return categories.stream().map(CategoryDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Category> dtosToEntities(List<CategoryDto> categoryDtos){
        return categoryDtos.stream().map(CategoryDto::dtoToEntity).collect(Collectors.toList());
    }


}
