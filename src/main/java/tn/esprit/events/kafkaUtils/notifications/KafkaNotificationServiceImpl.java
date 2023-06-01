package tn.esprit.events.kafkaUtils.notifications;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.NotificationDto;
import tn.esprit.events.kafkaUtils.BrokerProducerService;
import tn.esprit.events.kafkaUtils.exceptions.MapperException;

@Service
public class KafkaNotificationServiceImpl implements KafkaNotificationService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final BrokerProducerService brokerProducerService;
    private final Environment env;

    public KafkaNotificationServiceImpl(BrokerProducerService brokerProducerService, Environment env) {
        this.brokerProducerService = brokerProducerService;
        this.env = env;
    }

    @Override
    public void send(NotificationDto notification) {
        brokerProducerService.sendMessage("notification", toJson(notification));
    }


    /**
     * Convert Object to json
     *
     * @param object object
     * @return string json
     */
    private <T> String toJson(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new MapperException(e.getMessage());
        }
    }
}
