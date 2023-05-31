package tn.esprit.events.dtos;

import lombok.*;
import org.apache.catalina.LifecycleState;
import tn.esprit.events.entities.React;
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

public class ReactDto {


    private Long id;

    private UserDto user;

    private boolean liked;


    public static ReactDto entityToDto(React react){
        return ReactDto.builder().id(react.getId()).user(UserKcService.findById(react.getUserId())).liked(react.isLiked()).build();
    }

    public static React dtoToEntity(ReactDto reactDto){
        return React.builder().id(reactDto.getId()).userId(reactDto.user.getId()).liked(reactDto.isLiked()).build();
    }

    public static List<ReactDto> entitiesToDtos(List<React> reacts){
        return reacts.stream().map(ReactDto::entityToDto).collect(Collectors.toList());
    }

    public static List<React> dtosToEntities(List<ReactDto> reactDtos){
        return reactDtos.stream().map(ReactDto::dtoToEntity).collect(Collectors.toList());
    }



}
