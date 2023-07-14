package tn.esprit.events.kafkaUtils.notifications;


import tn.esprit.events.dtos.NotificationDto;

public interface KafkaNotificationService {

    /**
     * Send notification
     * @param notification model of notification
     */
    void send(NotificationDto notification);
}
