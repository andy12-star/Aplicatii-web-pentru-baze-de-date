package com.andy.master.model.dto.response;

import com.andy.master.model.enums.Status;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
    private Long id;
    private String eventTitle;
    private String userName;
    private Integer numberOfSeats;
    private Status status;
}
