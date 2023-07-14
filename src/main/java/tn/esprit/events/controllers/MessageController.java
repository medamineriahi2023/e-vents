package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.dtos.ReactDto;
import tn.esprit.events.services.IMessageService;

import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController implements AbstractCrudController<MessageDto> {

    private final IMessageService iMessageService;
    @Override
    public MessageDto save(@RequestBody MessageDto messageDto) {
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

    @GetMapping("{userId}/messages")
    public List<MessageDto> getUserMessages(@PathVariable("userId") String userId){
        List<MessageDto> messages = this.iMessageService.getByReceiver(userId) ;
        return messages ;
    }

    @PostMapping("/{messageId}/react")
    public MessageDto addReactToMessage(@RequestBody ReactDto react, @PathVariable("messageId")String messageId){
        MessageDto messageDto  = this.iMessageService.setMessageReact(react,messageId);
        return messageDto;
    }

    @PostMapping("/{messageId}/seen")
    public MessageDto isSeen (@PathVariable("messageId")String messageId){
        MessageDto messageDto  = this.iMessageService.setSeen(messageId);
        return messageDto;
    }


}


