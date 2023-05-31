package tn.esprit.events.dtos;

import lombok.*;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Location;

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
public class LocationDto {

    private Long id;
    private String govName;
    private boolean archived;

    public static LocationDto entityToDto(Location location){
        return LocationDto.builder().govName(location.getGovName()).id(location.getId()).archived(location.isArchived()).build();
    }

    public static Location dtoToEntity(LocationDto locationDto){
        return Location.builder().govName(locationDto.getGovName()).id(locationDto.getId()).archived(locationDto.isArchived()).build();
    }

    public static List<LocationDto> entitiesToDtos(List<Location> locations){
        return locations.stream().map(LocationDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Location> dtosToEntities(List<LocationDto> locationDtos){
        return locationDtos.stream().map(LocationDto::dtoToEntity).collect(Collectors.toList());
    }



}
