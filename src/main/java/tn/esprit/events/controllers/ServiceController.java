package tn.esprit.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.CategoryDto;
import tn.esprit.events.dtos.ServiceDto;
import tn.esprit.events.exceptions.EntityNotFoundException;
import tn.esprit.events.services.IServiceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class ServiceController implements AbstractCrudController<ServiceDto> {

    private final IServiceService iServiceService;
    @Override
    public ServiceDto save(ServiceDto serviceDto) {
        return iServiceService.save(serviceDto);
    }

    @Override
    public List<ServiceDto> getAll() {
        return iServiceService.getAll();
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) { return iServiceService.update(serviceDto);
    }

    @PatchMapping(path = "/{id}")
    public ServiceDto updatePatch(@RequestBody Map<Object,Object> fields, @PathVariable Long id) throws EntityNotFoundException {
        return iServiceService.updatePatch(fields,id);
    }
}
