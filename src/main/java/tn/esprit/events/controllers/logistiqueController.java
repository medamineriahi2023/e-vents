//package tn.esprit.events.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import tn.esprit.events.controllers.abstracts.AbstractCrudController;
//import tn.esprit.events.dtos.LogistiqueDto;
//import tn.esprit.events.services.ILogistiqueService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("logistique")
//@RequiredArgsConstructor
//public class logistiqueController implements AbstractCrudController<LogistiqueDto> {
//
//    private final ILogistiqueService iLogistiqueService;
//    @Override
//    public LogistiqueDto save(LogistiqueDto logistiqueDto) {
//        return iLogistiqueService.save(logistiqueDto);
//    }
//
//    @Override
//    public List<LogistiqueDto> getAll() {
//        return iLogistiqueService.getAll();
//    }
//
//    @Override
//    public LogistiqueDto update(LogistiqueDto logistiqueDto) {
//        return iLogistiqueService.update(logistiqueDto);
//    }
//}
