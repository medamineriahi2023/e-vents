package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Publication;
import tn.esprit.events.entities.Topic;

import java.time.LocalDate;
import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
   // List<Publication> getPublicationByType(Topic topic);
    //List<Publication> getPublicationByDate (LocalDate date) ;

}
