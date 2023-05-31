package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.Notification;
import tn.esprit.events.entities.NotificationType;
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

public class NotificationDto {


    private Long id;
    private NotificationType type;
    private UserDto sender;
    private  long idEvent;
    private UserDto receiver;
    //TODO missing attributes
    public static NotificationDto entityToDto(Notification notification){
        return NotificationDto.builder().id(notification.getId()).type(notification.getType()).sender(UserKcService.findById(notification.getSenderId()))
                .idEvent(notification.getIdEvent()).receiver(UserKcService.findById(notification.getReceiverId())).build();
    }

    public static Notification dtoToEntity(NotificationDto notificationDto){
        return Notification.builder().id(notificationDto.getId()).type(notificationDto.getType()).
                senderId(notificationDto.getSender().getId()).idEvent(notificationDto.getIdEvent()).
                receiverId(notificationDto.getReceiver().getId()).build();
    }

    public static List<NotificationDto> entitiesToDtos(List<Notification> notifications){
        return notifications.stream().map(NotificationDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Notification> dtosToEntities(List<NotificationDto> notificationDtos){
        return notificationDtos.stream().map(NotificationDto::dtoToEntity).collect(Collectors.toList());
    }


}
