package com.andy.master.service.api;

import com.andy.master.model.dto.request.ReservationRequest;
import com.andy.master.model.dto.response.ReservationResponse;

import java.util.List;

public interface ReservationService {
    ReservationResponse createReservation(ReservationRequest request);
    List<ReservationResponse> getReservationsByUser(Long userId);
    List<ReservationResponse> getReservationsByEvent(Long eventId);
    void cancelReservation(Long reservationId);
}
