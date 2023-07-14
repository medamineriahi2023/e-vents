package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Notification;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.kafkaUtils.notifications.KafkaNotificationService;
import tn.esprit.events.repositories.NotificationRepository;
import tn.esprit.events.services.IEventService;
import tn.esprit.events.services.INotificationService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final KafkaNotificationService kafkaNotificationService;
    private final IEventService eventService;
    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        NotificationDto not = new NotificationDto(eventService);
        NotificationDto notificationDto1 = NotificationDto.entityToDto(notificationRepository.save(NotificationDto.dtoToEntity(notificationDto)));
        kafkaNotificationService.send(notificationDto1);
        return notificationDto1;

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

    @Override
    public NotificationDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        Notification notification = notificationRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("notification not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("notification not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Category.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, notification, value);
        });

        return update(NotificationDto.entityToDto(notification));
    }
}
