package tn.esprit.events.kafkaUtils.messages;

import tn.esprit.events.dtos.MessageDto;

public interface KafkaMessageService {

        void send(MessageDto messageDto);

}
