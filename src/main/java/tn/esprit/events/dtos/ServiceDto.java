package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.Service;

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

public class ServiceDto {


    private Long id;
    private String serviceName;

    // list of users ids joined by ","
    private String staffs;


    public static ServiceDto entityToDto(Service service){
        return ServiceDto.builder().id(service.getId()).serviceName(service.getServiceName()).staffs(service.getStaffs()).build();
    }

    public static Service dtoToEntity(ServiceDto serviceDto){
        return Service.builder().id(serviceDto.getId()).serviceName(serviceDto.getServiceName()).staffs(serviceDto.getStaffs()).build();
    }

    public static List<ServiceDto> entitiesToDtos(List<Service> services){
        return services.stream().map(ServiceDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Service> dtosToEntities(List<ServiceDto> serviceDtos){
        return serviceDtos.stream().map(ServiceDto::dtoToEntity).collect(Collectors.toList());
    }


}