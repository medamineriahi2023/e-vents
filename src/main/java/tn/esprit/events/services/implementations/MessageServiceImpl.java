package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Message;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.handlers.ErrorCodes;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.MessageRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IMessageService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService {

    private final MessageRepository messageRepository;
    @Override
    public MessageDto save(MessageDto messageDto) {
        return MessageDto.entityToDto(messageRepository.save(MessageDto.dtoToEntity(messageDto)));
    }

    @Override
    public List<MessageDto> getAll() {
        return MessageDto.entitiesToDtos(messageRepository.findAll());
    }

    @Override
    public MessageDto update(MessageDto messageDto) {
        return MessageDto.entityToDto(messageRepository.save(MessageDto.dtoToEntity(messageDto)));
    }

    @Override
    public MessageDto getById(Long id) {
        return MessageDto.entityToDto(messageRepository.findById(id).get());
    }

    @Override
    public MessageDto updatePatch(Map<Object, Object> fields, Long id) throws EntityNotFoundException {
        Message message = messageRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("message not found", ErrorCodes.ENTITY_NOT_FOUND, new ArrayList<>(Collections.singleton("message not found"))));

        fields.forEach((key, value) -> {

            Field field = ReflectionUtils.findField(Message.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, message, value);
        });

        return update(MessageDto.entityToDto(message));

    }
}
