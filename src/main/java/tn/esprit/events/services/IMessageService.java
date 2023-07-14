package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.Map;

public interface IMessageService extends ICrudService<MessageDto> {

    MessageDto updatePatch(Map<Object, Object> fields , Long id) throws EntityNotFoundException;

}
