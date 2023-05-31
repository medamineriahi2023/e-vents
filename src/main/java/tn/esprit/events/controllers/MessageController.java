package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IMessageService;

import java.util.List;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController implements AbstractController<MessageDto> {

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
}
