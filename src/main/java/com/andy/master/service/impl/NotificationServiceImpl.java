package com.andy.master.service.impl;

import com.andy.master.model.dto.request.NotificationRequest;
import com.andy.master.model.dto.response.NotificationResponse;
import com.andy.master.model.entity.Notification;
import com.andy.master.model.entity.User;
import com.andy.master.repository.NotificationRepository;
import com.andy.master.repository.UserRepository;
import com.andy.master.service.api.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationResponse create(NotificationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = Notification.builder()
                .user(user)
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .build();

        return mapToDto(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationResponse> getByUserId(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    private NotificationResponse mapToDto(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
