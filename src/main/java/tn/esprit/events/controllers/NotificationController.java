package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.kafkaUtils.notifications.KafkaNotificationService;
import tn.esprit.events.services.INotificationService;

import java.util.List;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class NotificationController implements AbstractCrudController<NotificationDto> {

    private final INotificationService iNotificationService;
    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        return iNotificationService.save(notificationDto);
    }

    @Override
    public List<NotificationDto> getAll() {
        return iNotificationService.getAll();
    }

    @Override
    public NotificationDto update(NotificationDto notificationDto) {return iNotificationService.update(notificationDto);
    }
}
