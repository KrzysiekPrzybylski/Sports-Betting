package com.crud.bets.mapper;

import com.crud.bets.domain.Event;
import com.crud.bets.domain.dto.EventDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventMapper {

    public Event mapToEvent(EventDto eventDto) {
        return new Event(
                eventDto.getEventId(),
                eventDto.getCategoryName(),
                eventDto.getDateTime(),
                eventDto.getTeamOneName(),
                eventDto.getTeamTwoName(),
                eventDto.getTeamOneScore(),
                eventDto.getTeamTwoScore(),
                eventDto.isFinished()
        );
    }
    public EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .eventId(event.getEventId())
                .categoryName(event.getCategory())
                .dateTime(event.getDateTime())
                .teamOneName(event.getTeamOneName())
                .teamTwoName(event.getTeamTwoName())
                .teamOneScore(event.getTeamOneScore())
                .teamTwoScore(event.getTeamTwoScore())
                .build();
    }
    public List<EventDto> mapToEventDtoList(List<Event> events) {
        return events.stream()
                .map(this::mapToEventDto)
                .collect(Collectors.toList());
    }
}
