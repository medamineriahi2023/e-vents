package tn.esprit.events.services;

import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.entities.Message;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.List;

public interface IMessageService extends ICrudService<MessageDto> {


   List<MessageDto> getByReceiver(String userId) ;
   MessageDto setMessageReact(ReactDto react, String messageId);

   MessageDto setSeen(String messageId) ;


}
