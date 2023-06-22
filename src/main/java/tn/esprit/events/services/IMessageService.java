package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.entities.Message;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.List;

public interface IMessageService extends ICrudService<MessageDto> {


   List<Message> getByReceiver(String userId) ;

}
