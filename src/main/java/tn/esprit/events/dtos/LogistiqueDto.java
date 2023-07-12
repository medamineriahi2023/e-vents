package tn.esprit.events.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import org.apache.catalina.LifecycleState;
import tn.esprit.events.entities.Logistique;
import tn.esprit.events.utils.UserKcService;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogistiqueDto {


    private Long id;

    private UserDto user;


    public static LogistiqueDto entityToDto(Logistique logistique){
        return LogistiqueDto.builder().id(logistique.getId()).user(UserKcService.findById(logistique.getUserId())).build();
   }

    public static Logistique dtoToEntity(LogistiqueDto logistiqueDto){
        return Logistique.builder().id(logistiqueDto.getId()).userId(logistiqueDto.getUser().getId()).build();
    }
    public static List<LogistiqueDto> entitiesToDtos(List<Logistique> logistiques){
        return logistiques.stream().map(LogistiqueDto::entityToDto).collect(Collectors.toList());
  }

    public static List<Logistique> dtosToEntities(List<LogistiqueDto> logistiqueDtos){
        return logistiqueDtos.stream().map(LogistiqueDto::dtoToEntity).collect(Collectors.toList());
    }


}
