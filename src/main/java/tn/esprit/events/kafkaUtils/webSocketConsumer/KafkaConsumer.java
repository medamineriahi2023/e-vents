package tn.esprit.events.kafkaUtils.webSocketConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.kafkaUtils.exceptions.MapperException;

@Slf4j
@Component
public class KafkaConsumer {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "notification", groupId = "notification-group-id", containerFactory = "kakfaListenerContainerFactory")
    public void listenSenderEmail(String data) {

        NotificationDto notification = fromJson(data, NotificationDto.class);

        log.info("Consumed message: {} : testing" , data);
        template.convertAndSend("/topic/notif/"+notification.getReceiver().getId(), notification);

    }


    @KafkaListener(topics = "messages", groupId = "messages-group-id", containerFactory = "kakfaListenerContainerFactory")
    public void listenForMessages(String data) {

        MessageDto message = fromJson(data, MessageDto.class);

        log.info("Consumed message: {} : testing" , data);
        template.convertAndSend("/topic/message/"+message.getReceiver().getId(), message);

    }

    /**
     * Convert json to Object
     * @param json String json value
     * @param clazz Instances of the class
     * @param <T> Object Class
     * @return Object class
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        try {
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
