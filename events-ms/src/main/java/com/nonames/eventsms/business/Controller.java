package com.nonames.eventsms.business;

import com.nonames.eventsms.models.Event;
import com.nonames.eventsms.models.Participant;
import com.nonames.eventsms.persistence.EventRepository;
import com.nonames.eventsms.persistence.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping("/view-events-and-participants")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return ResponseEntity.ok(events);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create-event")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        if (eventRepository.existsById(event.getId()))
            throw new ResponseStatusException(CONFLICT, "Event UUID already exists");

        Event createdEvent = eventRepository.save(event);
        return ResponseEntity.ok(createdEvent);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/register-participant")
    public ResponseEntity<Event> registerParticipant(@RequestParam UUID participantId, @RequestParam UUID eventId) {
        fetchAndSaveParticipant(participantId);

        Optional<Event> event = eventRepository.findById(eventId);
        Optional<Participant> participant = participantRepository.findById(participantId);

        if (event.isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "Event UUID not found");
        if (participant.isEmpty())
            throw new ResponseStatusException(NOT_FOUND, "Participant UUID not found");

        event.get().getParticipants().add(participant.get());
        eventRepository.save(event.get());

        participant.get().getEvents().add(event.get());
        participantRepository.save(participant.get());

        return ResponseEntity.ok(event.get());
    }

    private Participant fetchAndSaveParticipant(UUID participantId){
        Participant participant = fetchParticipantData(participantId);
        Participant savedParticipant = participantRepository.save(participant);
        return savedParticipant;
    }

    private Participant fetchParticipantData(UUID participantId){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders header= new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.add("Accept", "application/json");

        MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
        HttpEntity<MultiValueMap<String, String>> requeteHttp =new HttpEntity<MultiValueMap<String, String>>(body, header);

        // TODO CONFIG & GET PARAM
        ResponseEntity<Participant> reponse = restTemplate.postForEntity("http://localhost:8081/get-participant-by-id?id=" + participantId.toString(), requeteHttp , Participant.class);

        return reponse.getBody();
    }
}
