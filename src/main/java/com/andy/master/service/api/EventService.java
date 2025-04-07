package com.andy.master.service.api;

import com.andy.master.model.dto.request.EventRequest;
import com.andy.master.model.dto.response.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest, Long userId);

    EventResponse getEventById(Long eventId);

    List<EventResponse> getAllEvents();

    List<EventResponse> getEventsByUserId(Long userId);

    EventResponse updateEvent(EventRequest eventRequest, Long eventId);

    void deleteEvent(Long eventId);
}
