package tn.esprit.events.controllers.abstracts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AbstractCrudController<T> {


    @PostMapping
    T save (@RequestBody T t);

    @GetMapping
    List<T> getAll ();

    @PutMapping
    T update (@RequestBody T t);

}
