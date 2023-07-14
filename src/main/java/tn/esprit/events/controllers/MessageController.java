package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.IMessageService;

import java.util.List;
import java.util.Map;

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

    @PatchMapping(path = "/{id}")
    public MessageDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iMessageService.updatePatch(fields,id);
    }

}
