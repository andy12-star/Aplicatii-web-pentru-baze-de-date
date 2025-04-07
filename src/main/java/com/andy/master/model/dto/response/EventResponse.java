package com.andy.master.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EventResponse {
    private Long id;

    private String title;

    private String description;

    private String location;

    private LocalDate date;

    private LocalTime time;

    private Integer availableSeats;

    private String category;

    private Set<String> tags;
}
