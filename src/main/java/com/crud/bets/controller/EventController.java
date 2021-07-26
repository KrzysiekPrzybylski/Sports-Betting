package com.crud.bets.controller;

import com.crud.bets.domain.dto.EventDto;
import com.crud.bets.exception.EventNotFoundException;
import com.crud.bets.mapper.EventMapper;
import com.crud.bets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventMapper eventMapper;
    private final EventService eventService;

    @Autowired
    public EventController(EventMapper eventMapper, EventService eventService) {
        this.eventMapper = eventMapper;
        this.eventService = eventService;
    }
    @GetMapping
    public List<EventDto> getEvents() {
        return eventMapper.mapToEventDtoList(eventService.getEvents());
    }
    @GetMapping("/{eventId")
    public EventDto getEvent(@PathVariable long eventId) {
        return eventMapper.mapToEventDto(eventService.getEvent(eventId));
    }
    @PostMapping
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventMapper.mapToEventDto(eventService.addEvent(eventMapper.mapToEvent(eventDto)));
    }
    @PatchMapping("/{eventId}")
    public EventDto editEvent(@PathVariable long eventId, @RequestBody EventDto eventDto) throws EventNotFoundException {
        return eventMapper.mapToEventDto(eventService.editEvent(eventId,eventDto));
    }
    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable long eventId) {
        eventService.deleteEvent(eventId);
    }
}
