package com.andy.master.service.api;

import com.andy.master.model.dto.request.NotificationRequest;
import com.andy.master.model.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse create(NotificationRequest request);
    List<NotificationResponse> getByUserId(Long userId);
    void delete(Long id);
}
