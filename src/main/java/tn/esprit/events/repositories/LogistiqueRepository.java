package tn.esprit.events.repositories;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Logistique;

<<<<<<< Updated upstream
public interface LogistiqueRepository extends JpaRepository<Logistique, Long> {
=======
import java.util.List;

public interface LogistiqueRepository extends JpaRepository<Logistique, Long> {

    List<Logistique> findByUserId(String userId);
>>>>>>> Stashed changes
}
