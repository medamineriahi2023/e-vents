package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.*;
import tn.esprit.events.entities.Message;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.MessageRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IMessageService;
import tn.esprit.events.services.IReactService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService {

    private final MessageRepository messageRepository;
    private final IReactService iReactService;
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
    public List<MessageDto> getByReceiver(String userId) {
        List<MessageDto> messageDtos = MessageDto.entitiesToDtos(this.messageRepository.findByIdReceiver(userId)) ;
        return messageDtos ;
    }

    @Override
    @Transactional
    public MessageDto setMessageReact(ReactDto react, String messageId) {
        ReactDto savedReact = iReactService.save(react);
        Message message = messageRepository.findById(Long.parseLong(messageId)).get();
        message.setReact(ReactDto.dtoToEntity(savedReact));
        MessageDto messageDto = MessageDto.entityToDto(message);
        return messageDto;
    }

    @Override
    @Transactional
    public MessageDto setSeen (String messageId) {
        Message message = messageRepository.findById(Long.parseLong(messageId)).get();
        message.setSeen(true) ;
        MessageDto messageDto = MessageDto.entityToDto(message);
        return messageDto ;
    }



}
