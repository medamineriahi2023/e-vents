package tn.esprit.events.services;

import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.dtos.UserDto;
import tn.esprit.events.services.abstracts.ICrudService;

import java.util.List;


public interface IEventService extends ICrudService<EventDto> {


    EventDto archiveEvent(String id);
    EventDto rescheduleEvent(EventDto eventDto);

    EventDto addParticipant(UserDto userDto, String eventId);
    EventDto addStaff(UserDto userDto, String eventId);

    List<EventDto> identifyCloseEvent(String userId);


}
