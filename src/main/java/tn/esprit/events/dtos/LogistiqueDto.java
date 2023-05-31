package tn.esprit.events.dtos;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder

import org.apache.catalina.LifecycleState;
import tn.esprit.events.entities.Logistique;

import java.util.List;
import java.util.stream.Collectors;

public class LogistiqueDto {


    private Long id;

//    public static LogistiqueDto entityToDto(Logistique logistique){
//        return LogistiqueDto.builder().id(logistique.getId()).build();
//    }
//
//    public static Logistique dtoToEntity(LogistiqueDto logistiqueDto){
//        return Logistique.builder().id(logistiqueDto.getId()).build();
//    }
//
//    public static List<LogistiqueDto> entitiesToDtos(List<Logistique> logistiques){
//        return logistiques.stream().map(LogistiqueDto::entityToDto).collect(Collectors.toList());
//    }
//
//    public static List<Logistique> dtosToEntities(List<LogistiqueDto> logistiqueDtos){
//        return logistiqueDtos.stream().map(LogistiqueDto::dtoToEntity).collect(Collectors.toList());
//    }


}
