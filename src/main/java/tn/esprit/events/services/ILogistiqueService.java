package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.LogistiqueDto;
<<<<<<< Updated upstream
import tn.esprit.events.services.abstracts.ICrudService;

public interface ILogistiqueService extends ICrudService<LogistiqueDto> {

=======
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.List;

public interface ILogistiqueService extends ICrudService<LogistiqueDto> {

    public List<LogistiqueDto> getLogistiquesByUser(String userId);


>>>>>>> Stashed changes

}
