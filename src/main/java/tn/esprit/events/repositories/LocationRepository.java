package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
