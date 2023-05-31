package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Logistique;

public interface LogistiqueRepository extends JpaRepository<Logistique, Long> {
}
