package tn.esprit.events.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private NotificationType type;
    private User senderId;
    private  long idEvent;
    private User receiverId;


}
