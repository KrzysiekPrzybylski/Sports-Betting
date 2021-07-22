package com.crud.bets.service;

import com.crud.bets.domain.Event;
import com.crud.bets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
    public Event getEvent(long eventId) {
        return eventRepository.getById(eventId);
    }
}
