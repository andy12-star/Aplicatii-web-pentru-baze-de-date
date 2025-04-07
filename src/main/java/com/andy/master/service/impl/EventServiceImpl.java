package com.andy.master.service.impl;

import com.andy.master.model.dto.request.EventRequest;
import com.andy.master.model.dto.response.EventResponse;
import com.andy.master.model.entity.Category;
import com.andy.master.model.entity.Event;
import com.andy.master.model.entity.Tag;
import com.andy.master.model.entity.User;
import com.andy.master.repository.CategoryRepository;
import com.andy.master.repository.EventRepository;
import com.andy.master.repository.TagRepository;
import com.andy.master.repository.UserRepository;
import com.andy.master.service.api.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public EventResponse createEvent(EventRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Set<Tag> tags = new HashSet<>(tagRepository.findAllById(request.getTagIds()));


        Event event = Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .date(request.getDate())
                .time(request.getTime())
                .availableSeats(request.getAvailableSeats())
                .user(user)
                .category(category)
                .tags(tags)
                .build();

        return mapToDto(eventRepository.save(event));
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return mapToDto(event);
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<EventResponse> getEventsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return eventRepository.findAll()
                .stream()
                .filter(event -> event.getUser().getId().equals(userId))
                .map(this::mapToDto)
                .toList();
    }



    @Override
    public EventResponse updateEvent(EventRequest request,Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Tag> tagList = tagRepository.findAllById(request.getTagIds());
        Set<Tag> tags = tagList.stream()
                .map(tag -> {
                    tag.setEvents(null);
                    return tag;
                })
                .collect(Collectors.toSet());

        event.setTags(tags);
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setLocation(request.getLocation());
        event.setDate(request.getDate());
        event.setTime(request.getTime());
        event.setAvailableSeats(request.getAvailableSeats());
        event.setCategory(category);
        event.setTags(tags);

        return mapToDto(eventRepository.save(event));
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private EventResponse mapToDto(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .date(event.getDate())
                .time(event.getTime())
                .availableSeats(event.getAvailableSeats())
                .category(event.getCategory().getName())
                .tags(event.getTags().stream().map(Tag::getName).collect(Collectors.toSet()))
                .build();
    }
}
