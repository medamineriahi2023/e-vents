package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.Map;

public interface INotificationService extends ICrudService<NotificationDto> {

    NotificationDto updatePatch(Map<Object, Object> fields , Long id) throws EntityNotFoundException;

}
