package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Event;
import tn.esprit.events.entities.Type;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> getEventsByType(Type type);
    List<Event> getEventsByCategoryId(Long categoryId);
    List<Event> getEventsByNameContaining(String eventName);
    List<Event> getEventsByLocationNameContaining(String locationName);
    List<Event> getEventsByOrganizerId(String organizerId);
}
