package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Category;
import tn.esprit.events.entities.Comment;

public interface CategotyRepository extends JpaRepository<Category, Long> {
}
