package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractController;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.LogistiqueDto;
import tn.esprit.events.services.ICommentService;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.LogistiqueDto;
>>>>>>> Stashed changes
import tn.esprit.events.services.ILogistiqueService;

import java.util.List;

@RestController
@RequestMapping("logistique")
@RequiredArgsConstructor
<<<<<<< Updated upstream
public class logistiqueController implements AbstractController<LogistiqueDto> {

    private final ILogistiqueService iLogistiqueService;
=======
public class logistiqueController implements AbstractCrudController<LogistiqueDto> {

    private final ILogistiqueService iLogistiqueService;

>>>>>>> Stashed changes
    @Override
    public LogistiqueDto save(LogistiqueDto logistiqueDto) {
        return iLogistiqueService.save(logistiqueDto);
    }

    @Override
    public List<LogistiqueDto> getAll() {
        return iLogistiqueService.getAll();
    }

    @Override
    public LogistiqueDto update(LogistiqueDto logistiqueDto) {
        return iLogistiqueService.update(logistiqueDto);
    }
<<<<<<< Updated upstream
}
=======

    @GetMapping("user/{userId}")
    public List<LogistiqueDto> getLogistiquesByUser(@PathVariable("userId") String userId) {
        return iLogistiqueService.getLogistiquesByUser(userId);
    }
}



>>>>>>> Stashed changes
