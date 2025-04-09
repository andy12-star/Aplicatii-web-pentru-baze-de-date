package com.andy.master.service;

import com.andy.master.model.dto.request.EventRequest;
import com.andy.master.model.dto.response.EventResponse;
import com.andy.master.model.entity.*;
import com.andy.master.repository.CategoryRepository;
import com.andy.master.repository.EventRepository;
import com.andy.master.repository.TagRepository;
import com.andy.master.repository.UserRepository;
import com.andy.master.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent_success() {
        // Arrange
        EventRequest request = EventRequest.builder()
                .title("Concert")
                .description("Live music")
                .location("Arena")
                .date(LocalDate.of(2025, 5, 1))
                .time(LocalTime.of(19, 30))
                .availableSeats(150)
                .categoryId(1L)
                .tagIds(Set.of(1L, 2L))
                .build();

        User user = User.builder().id(1L).firstName("john").build();
        Category category = Category.builder().id(1L).name("Music").build();
        Tag tag1 = Tag.builder().id(1L).name("Rock").build();
        Tag tag2 = Tag.builder().id(2L).name("Live").build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(tagRepository.findAllById(request.getTagIds())).thenReturn(List.of(tag1, tag2));
        when(eventRepository.save(any(Event.class))).thenAnswer(invocation -> {
            Event saved = invocation.getArgument(0);
            saved.setId(10L); // simulăm salvarea cu ID generat
            return saved;
        });

        // Act
        EventResponse response = eventService.createEvent(request, 1L);

        // Assert
        assertNotNull(response);
        assertEquals("Concert", response.getTitle());
        assertEquals("Arena", response.getLocation());
        assertEquals("Music", response.getCategory());
        assertTrue(response.getTags().contains("Rock"));
        assertTrue(response.getTags().contains("Live"));
        assertEquals(10L, response.getId());
    }
}
