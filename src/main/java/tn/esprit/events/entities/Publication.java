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

public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private LocalDate date;
    private  String content;
    private Topic topic;


    //list of ids joined by ","
    @Column(length = 10000)
    private String users;
    private String creatorId;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<React> reacts;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Comment> comments;


}
