package tn.esprit.events.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String idSender;
    private String idReceiver;
    private LocalDate date;
    private boolean seen;
    private boolean archived;
    private String text;

    @OneToOne
    private React react;
}
