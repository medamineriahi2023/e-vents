package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.kafkaUtils.notifications.KafkaNotificationService;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.NotificationRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.INotificationService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final KafkaNotificationService kafkaNotificationService;
    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        kafkaNotificationService.send(notificationDto);
        return NotificationDto.entityToDto(notificationRepository.save(NotificationDto.dtoToEntity(notificationDto)));
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
}
