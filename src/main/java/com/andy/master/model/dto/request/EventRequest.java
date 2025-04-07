package com.andy.master.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequest {

    private String title;

    private String description;

    private String location;

    private LocalDate date;

    private LocalTime time;

    private Integer availableSeats;

    private Long categoryId;

    private Set<Long> tagIds;
}
