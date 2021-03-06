package com.crud.bets.mapper;

import com.crud.bets.domain.Category;
import com.crud.bets.domain.Event;
import com.crud.bets.domain.dto.EventDto;
import com.crud.bets.exception.CategoryNotFoundException;
import com.crud.bets.repository.CategoryRepository;
import com.crud.bets.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEvent(EventDto eventDto){
        Event event = new Event();
        event.setDateTime(eventDto.getDateTime());
        event.setTeamOneName(eventDto.getTeamOneName());
        event.setTeamTwoName(eventDto.getTeamTwoName());
        event.setTeamOneScore(eventDto.getTeamOneScore());
        event.setTeamTwoScore(eventDto.getTeamTwoScore());
        event.setFinished(Optional.of(eventDto.isFinished()).orElse(false));

        return event;
    }
    public EventDto mapToEventDto(Event event) {
        String categoryName = null;
        if(event.getCategory()!=null) {
            categoryName = event.getCategory().getName();
        }
        return EventDto.builder()
                .categoryName(categoryName)
                .dateTime(event.getDateTime())
                .eventId(event.getEventId())
                .teamOneName(event.getTeamOneName())
                .teamTwoName(event.getTeamTwoName())
                .teamOneScore(event.getTeamOneScore())
                .teamTwoScore(event.getTeamTwoScore())
                .finished(event.isFinished())
                .build();
    }
    public List<EventDto> mapToEventDtoList(List<Event> events) {
        return events.stream()
                .map(this::mapToEventDto)
                .collect(Collectors.toList());
    }

}
