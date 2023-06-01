package tn.esprit.events.kafkaUtils.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.kafkaUtils.BrokerProducerService;
import tn.esprit.events.kafkaUtils.exceptions.MapperException;

@Service
public class KafkaMessageServiceImpl implements KafkaMessageService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private final BrokerProducerService brokerProducerService;
    public KafkaMessageServiceImpl(BrokerProducerService brokerProducerService) {
        this.brokerProducerService = brokerProducerService;
    }

    @Override
    public void send(MessageDto messageDto) {
        brokerProducerService.sendMessage("messages", toJson(messageDto));
    }


    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
