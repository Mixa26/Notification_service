package com.CarRental.NotificationService.service;

import com.CarRental.NotificationService.dto.CreateNotificationHistoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationHistorySevice {

    Page<CreateNotificationHistoryDto> findAll(Pageable pageable);

    Page<CreateNotificationHistoryDto> findByToEmail(String toEmail, Pageable pageable);

    Page<CreateNotificationHistoryDto> findByFromEmail(String fromEmail, Pageable pageable);

    Page<CreateNotificationHistoryDto> findByNotificationType(NotificationType notificationType, Pageable pageable);

    Page<CreateNotificationHistoryDto> findByUserId(Long userId, Pageable pageable);
}
