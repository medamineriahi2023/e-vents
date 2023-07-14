package tn.esprit.events.services.abstracts;

import java.util.List;

public interface ICrudService<T> {

    T save(T t);
    List<T> getAll();
    T update(T t);
    T getById(Long id);

}
