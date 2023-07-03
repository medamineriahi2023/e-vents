package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Publication;


public interface PublicationRepository extends JpaRepository<Publication, Long> {
   // List<Publication> getPublicationByType(Topic topic);
    //List<Publication> getPublicationByDate (LocalDate date) ;

}
