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
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private NotificationType type;
    private String senderId;
    private  Long idEvent;
    private String receiverId;

    private Long publicationId;
    private Long reactId;
    private Long commentId;


}
