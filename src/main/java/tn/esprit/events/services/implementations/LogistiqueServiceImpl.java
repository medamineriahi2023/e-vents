package tn.esprit.events.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.LocationDto;
import tn.esprit.events.dtos.LogistiqueDto;
<<<<<<< Updated upstream
=======
import tn.esprit.events.dtos.UserDto;
>>>>>>> Stashed changes
import tn.esprit.events.repositories.CommentRepository;
import tn.esprit.events.repositories.LocationRepository;
import tn.esprit.events.repositories.LogistiqueRepository;
import tn.esprit.events.services.ICommentService;
import tn.esprit.events.services.ILogistiqueService;

import java.util.List;

<<<<<<< Updated upstream
//@Service
//@RequiredArgsConstructor
//public class LogistiqueServiceImpl implements ILogistiqueService {
//
////    private final LogistiqueRepository logistiqueRepository;
////    @Override
////    public LogistiqueDto save(LogistiqueDto logistiqueDto) {
////        return LogistiqueDto.entityToDto(logistiqueRepository.save(LocationDto.dtoToEntity(logistiqueDto)));
////    }
////
////    @Override
////    public List<LogistiqueDto> getAll() {
////        return LogistiqueDto.entitiesToDtos(logistiqueRepository.findAll());
////    }
////
////    @Override
////    public LogistiqueDto update(LogistiqueDto logistiqueDto) {
////        return LogistiqueDto.entityToDto(logistiqueRepository.save(LocationDto.dtoToEntity(logistiqueDto)));
////    }
//}
=======
@Service
@RequiredArgsConstructor
 public class LogistiqueServiceImpl implements ILogistiqueService {

    private final LogistiqueRepository logistiqueRepository;
   @Override
    public LogistiqueDto save(LogistiqueDto logistiqueDto) {
      return LogistiqueDto.entityToDto(logistiqueRepository.save(LogistiqueDto.dtoToEntity(logistiqueDto)));
    }

    @Override
    public List<LogistiqueDto> getAll() {
        return LogistiqueDto.entitiesToDtos(logistiqueRepository.findAll());
    }

   @Override
    public LogistiqueDto update(LogistiqueDto logistiqueDto) {
        return LogistiqueDto.entityToDto(logistiqueRepository.save(LogistiqueDto.dtoToEntity(logistiqueDto)));
    }

    @Override
    public LogistiqueDto getById(Long id) {
        return null;
    }

    @Override
    public List<LogistiqueDto> getLogistiquesByUser(String userId) {
       List<LogistiqueDto> logistiqueDtoList = LogistiqueDto.entitiesToDtos(logistiqueRepository.findByUserId(userId));
        return logistiqueDtoList;
    }

}
>>>>>>> Stashed changes
