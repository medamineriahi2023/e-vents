package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.MessageRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.IMessageService;

import java.util.List;

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
}
