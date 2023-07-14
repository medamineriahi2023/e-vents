package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.kafkaUtils.notifications.KafkaNotificationService;
import tn.esprit.events.services.INotificationService;

import java.util.List;
import java.util.Map;

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

    @PatchMapping(path = "/{id}")
    public NotificationDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iNotificationService.updatePatch(fields,id);
    }
}
