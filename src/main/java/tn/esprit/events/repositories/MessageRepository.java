package tn.esprit.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.events.dtos.MessageDto;
import tn.esprit.events.entities.Comment;
import tn.esprit.events.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<MessageDto> findByIdReceiver(String idReceiver);

}
