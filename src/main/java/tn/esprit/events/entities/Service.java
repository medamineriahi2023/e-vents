package tn.esprit.events.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String serviceName;
    private List<String> staffs;


}
