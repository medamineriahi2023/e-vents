package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.Notification;
import tn.esprit.events.entities.NotificationType;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IEventService;
import tn.esprit.events.services.IPublicationService;
import tn.esprit.events.services.IReactService;
import tn.esprit.events.utils.UserKcService;
import tn.esprit.events.utils.UserKcService;


import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDto {

    private static IEventService eventService;
    private static IPublicationService publicationService;
    private static ICommentService commentService;
    private static IReactService reactService;
    public NotificationDto(IEventService eventService) {
        NotificationDto.eventService = eventService;
    }

    private Long id;
    private NotificationType type;
    private UserDto sender;
    private EventDto eventDto;
    private UserDto receiver;

    private PublicationDto publication;
    private ReactDto react;
    private CommentDto comment;

    //TODO missing attributes
    public static NotificationDto entityToDto(Notification notification){
        return NotificationDto.builder().id(notification.getId()).type(notification.getType()).sender(UserKcService.findById(notification.getSenderId()))
                .eventDto(notification.getIdEvent() != null ? eventService.getById(notification.getIdEvent()) : null)
                .receiver(notification.getReceiverId() != null ? UserKcService.findById(notification.getReceiverId()) : null)
                .publication(notification.getPublicationId() != null ? publicationService.getById(notification.getPublicationId()) : null)
                .react(notification.getReactId() != null ? reactService.getById(notification.getReactId()) : null)
                .comment(notification.getCommentId() != null ? commentService.getById(notification.getCommentId()) : null)
                .build();
    }

    public static Notification dtoToEntity(NotificationDto notificationDto){
        return Notification.builder().id(notificationDto.getId()).type(notificationDto.getType())
                .senderId(notificationDto.getSender().getId()).idEvent(notificationDto.getEventDto().getId())
                .receiverId(notificationDto.getReceiver().getId())
                .commentId(notificationDto.getComment() != null ? notificationDto.getComment().getId() : null)
                .publicationId(notificationDto.getPublication() != null ? notificationDto.getPublication().getId() : null)
                .reactId(notificationDto.getReact() != null ? notificationDto.getReact().getId() : null)
                .build();
    }

    public static List<NotificationDto> entitiesToDtos(List<Notification> notifications){
        return notifications.stream().map(NotificationDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Notification> dtosToEntities(List<NotificationDto> notificationDtos){
        return notificationDtos.stream().map(NotificationDto::dtoToEntity).collect(Collectors.toList());
    }


}
