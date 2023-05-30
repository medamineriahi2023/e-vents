package tn.esprit.events.entities;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String name;
    private LocalDate dateDebutEvent;
    private LocalDate dateFinEvent;
    private Type type;
    private Visibility visibilioty;
    private Category catygory;
    private String organierId;
    private List<String> staffs;
    private List<String> participants;
    private String backGroundImage;
    private String eventImage;
    private String video;
    private boolean archived;
    private String description;
    private Location location ;
    private String locationName;
    private String rue;
    private  String zipCode;


}
