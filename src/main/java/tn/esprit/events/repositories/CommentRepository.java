package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long> {
}
