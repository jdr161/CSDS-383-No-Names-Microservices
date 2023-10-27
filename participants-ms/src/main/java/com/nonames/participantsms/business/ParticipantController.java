package com.nonames.layered.business;

import com.nonames.layered.models.Event;
import com.nonames.layered.models.Participant;
import com.nonames.layered.persistence.EventRepository;
import com.nonames.layered.persistence.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping("/view-events")
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

    @GetMapping("/view-participants")
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return ResponseEntity.ok(participants);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create-participant")
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {
        if (participantRepository.existsById(participant.getId()))
            throw new ResponseStatusException(CONFLICT, "Participant UUID already exists");

        Participant createdParticipant = participantRepository.save(participant);
        return ResponseEntity.ok(createdParticipant);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/register-participant")
    public ResponseEntity<Event> registerParticipant(@RequestParam UUID participantId, @RequestParam UUID eventId) {
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
}