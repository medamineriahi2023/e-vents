package tn.esprit.events.services;

import tn.esprit.events.dtos.CommentDto;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.entities.Event;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.Date;
import java.util.List;


public interface IEventService extends ICrudService<EventDto> {

    List<Event> getAllEvents();
    Event createEvent(Event event);
    Event updateEvent(long id, Event event);

    Event getEventById(long id);
    Event archiveEvent(long id);
    Event rescheduleEvent(long id, Date dateDebut, Date dateFin);
    Event createOnSiteEvent(Event event);


}
