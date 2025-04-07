package com.andy.master.service.impl;

import com.andy.master.model.dto.request.ReservationRequest;
import com.andy.master.model.dto.response.ReservationResponse;
import com.andy.master.model.entity.*;
import com.andy.master.model.enums.Status;
import com.andy.master.repository.*;
import com.andy.master.service.api.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public ReservationResponse createReservation(ReservationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableSeats() < request.getNumberOfSeats()) {
            throw new RuntimeException("Not enough available seats");
        }

        event.setAvailableSeats(event.getAvailableSeats() - request.getNumberOfSeats());
        eventRepository.save(event);

        Reservation reservation = Reservation.builder()
                .user(user)
                .event(event)
                .numberOfSeats(request.getNumberOfSeats())
                .reservationDate(LocalDate.now())
                .status(Status.CONFIRMED)
                .build();

        return mapToDto(reservationRepository.save(reservation));
    }

    @Override
    public List<ReservationResponse> getReservationsByUser(Long userId) {
        return reservationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationResponse> getReservationsByEvent(Long eventId) {
        return reservationRepository.findByEventId(eventId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Event event = reservation.getEvent();
        event.setAvailableSeats(event.getAvailableSeats() + reservation.getNumberOfSeats());
        eventRepository.save(event);

        reservation.setStatus(Status.CANCELLED);
        reservationRepository.save(reservation);
    }

    private ReservationResponse mapToDto(Reservation reservation) {
        return ReservationResponse.builder()
                .id(reservation.getId())
                .eventTitle(reservation.getEvent().getTitle())
                .userName(reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName())
                .numberOfSeats(reservation.getNumberOfSeats())
                .status(reservation.getStatus())
                .build();
    }
}
