package tn.esprit.events.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private LocalDate dateDebutEvent;
    private LocalDate dateFinEvent;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Visibility visibility;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;


    private String organizerId;

    //Its a list joined by ,
    private String staffs;
    //Its a list joined by ,
    private String participants;


    private String backGroundImage;
    private String eventImage;
    private String video;
    private boolean archived;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location ;
    private String locationName;
    private String rue;
    private  String zipCode;


}
