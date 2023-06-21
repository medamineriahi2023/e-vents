package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.services.IMessageService;

import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController implements AbstractCrudController<MessageDto> {

    private final IMessageService iMessageService;
    @Override
    public MessageDto save(MessageDto messageDto) {
        return iMessageService.save(messageDto);
    }

    @Override
    public List<MessageDto> getAll() {
        return iMessageService.getAll();
    }

    @Override
    public MessageDto update(MessageDto messageDto) {
        return iMessageService.update(messageDto);
    }
    public List<MessageDto> getUserMessages(@PathVariable("userId") String userId){
        List<MessageDto> messages = this.iMessageService.getByReceiver(userId) ;
        return messages ;
    }
}


