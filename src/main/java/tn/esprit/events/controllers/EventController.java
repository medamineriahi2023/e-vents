package tn.esprit.events.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Events;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.events.controllers.abstracts.AbstractCrudController;
import tn.esprit.events.dtos.EventDto;
import tn.esprit.events.services.IEventService;

import java.util.Calendar;
import java.util.List;

import static com.google.api.client.googleapis.testing.auth.oauth2.MockTokenServerTransport.JSON_FACTORY;

@RestController
@RequestMapping("event")
@RequiredArgsConstructor
public class EventController implements AbstractCrudController<EventDto> {

    private final IEventService iEventService;
    @Override
    public EventDto save(EventDto eventDto) {
        return iEventService.save(eventDto);
    }

    @Override
    public List<EventDto> getAll() {
        return iEventService.getAll();
    }

    @Override
    public EventDto update(EventDto eventDto) {
        return iEventService.update(eventDto);
    }

    public ResponseEntity<String> getEvents(@AuthenticationPrincipal OAuth2User oAuth2User,
                                            @RequestParam(value = "sdate") String sdate,
                                            @RequestParam(value = "edate") String edate,
                                            @RequestParam(value = "q") String q) {
        com.google.api.services.calendar.model.Events eventList;
        String message;
        try {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User)oAuth2User;
            String token = customOAuth2User.getToken();
            GoogleCredential credential = new GoogleCredential().setAccessToken(token);

            final DateTime date1 = new DateTime(sdate + "T00:00:00");
            final DateTime date2 = new DateTime(edate + "T23:59:59");

            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName("Events app").build();
            Events events = service.events();
            eventList = events.list("primary").setTimeZone("Africa/Douala").setTimeMin(date1).setTimeMax(date2).setQ(q).execute();
            message = eventList.getItems().toString();
            System.out.println("My:" + eventList.getItems());
        } catch (Exception e) {

            message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
