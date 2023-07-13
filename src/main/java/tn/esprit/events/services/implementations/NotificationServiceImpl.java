package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.kafkaUtils.notifications.KafkaNotificationService;
import tn.esprit.events.repositories.NotificationRepository;
import tn.esprit.events.services.IEventService;
import tn.esprit.events.services.INotificationService;
import tn.esprit.events.services.IPublicationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final KafkaNotificationService kafkaNotificationService;
    private final IEventService eventService;
    private final IPublicationService publicationService;
    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        NotificationDto not = new NotificationDto(eventService);
        NotificationDto notificationDto1 = NotificationDto.entityToDto(notificationRepository.save(NotificationDto.dtoToEntity(notificationDto)));
        kafkaNotificationService.send(notificationDto1);
        return notificationDto1;
        NotificationDto newNotification = NotificationDto.entityToDto(notificationRepository.save(NotificationDto.dtoToEntity(notificationDto)));
        kafkaNotificationService.send(newNotification);
        switch (newNotification.getType()){
            case PARTICIPANT_INVITE_RESPONSE: this.eventService.addParticipant(newNotification.getReceiver(), newNotification.getEventDto().getId().toString());break;
            case ORGANIZER_INVITE_RESPONSE: this.eventService.addParticipant(newNotification.getSender(), newNotification.getEventDto().getId().toString());break;
            case STAFF_RECRUITMENT_RESPONSE: this.eventService.addStaff(newNotification.getReceiver(), newNotification.getEventDto().getId().toString());break;
            case STAFF_DEMAND_RESPONSE: this.eventService.addStaff(newNotification.getSender(), newNotification.getEventDto().getId().toString());break;
            //case PUB_REACTION: this.publicationService.changePublicationReacts(notificationDto.getReact(), notificationDto.getPublication().getId());break;
        }
        return newNotification;

    }

    @Override
    public List<NotificationDto> getAll() {
        return NotificationDto.entitiesToDtos(notificationRepository.findAll());
    }

    @Override
    public NotificationDto update(NotificationDto notificationDto) {
        return NotificationDto.entityToDto(notificationRepository.save(NotificationDto.dtoToEntity(notificationDto)));
    }

    @Override
    public NotificationDto getById(Long id) {
        return NotificationDto.entityToDto(notificationRepository.findById(id).get());
    }

    void checkNotificationPropertiesAreNotNull(NotificationDto notificationDto){
        Assert.isNull(notificationDto.getType(), "Notification Type can't be null");
        Assert.isNull(notificationDto.getSender(), "No Notification Sender");
        Assert.isNull(notificationDto.getReceiver(), "No notification Receiver");
        Assert.isNull(notificationDto.getEventDto(), "No event specified");
    }
}
