package com.crud.bets.service;

import com.crud.bets.domain.Event;
import com.crud.bets.domain.dto.EventDto;
import com.crud.bets.exception.EventNotFoundException;
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
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
    public Event editEvent(long eventId, EventDto eventDto) throws EventNotFoundException{
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        if(eventDto.getDateTime() !=null) {
            event.setDateTime(eventDto.getDateTime());
        }
        if(eventDto.getTeamOneScore() !=null && !eventDto.getTeamOneName().equals("")) {
            event.setTeamOneName(eventDto.getTeamOneName());
        }
        if (eventDto.getTeamTwoName() != null && !eventDto.getTeamTwoName().equals("")) {
            event.setTeamTwoName(eventDto.getTeamTwoName());
        }
        if (eventDto.getTeamOneScore() != null) {
            event.setTeamOneScore(eventDto.getTeamOneScore());
        }
        if (eventDto.getTeamTwoScore() != null) {
            event.setTeamTwoScore(eventDto.getTeamTwoScore());
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }
}
