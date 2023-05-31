package tn.esprit.events.dtos;


import lombok.*;
import tn.esprit.events.entities.Location;
import tn.esprit.events.entities.Message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MessageDto {

    private Long id;
    private String idSender;
    private String idReceiver;
    private LocalDate date;
    private boolean seen;
    private boolean archived;

    public static MessageDto entityToDto(Message message){
        return MessageDto.builder().id(message.getId()).idSender(message.getIdSender()).idReceiver(message.getIdReceiver()).date(message.getDate()).seen(message.isSeen()).archived(message.isArchived()).build();
    }

    public static Message dtoToEntity(MessageDto messageDto){
        return Message.builder().id(messageDto.getId()).idSender(messageDto.getIdSender()).idReceiver(messageDto.getIdReceiver()).date(messageDto.getDate()).seen(messageDto.isSeen()).archived(messageDto.isArchived()).build();
    }

    public static List<MessageDto> entitiesToDtos(List<Message> messages){
        return messages.stream().map(MessageDto::entityToDto).collect(Collectors.toList());
    }

    public static List<Message> dtosToEntities(List<MessageDto> messageDtos){
        return messageDtos.stream().map(MessageDto::dtoToEntity).collect(Collectors.toList());
    }


}
