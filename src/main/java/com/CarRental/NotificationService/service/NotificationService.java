package com.CarRental.NotificationService.service;

import com.CarRental.NotificationService.dto.CreateNotificationDto;
import com.CarRental.NotificationService.dto.NotificationDto;
import com.CarRental.NotificationService.dto.notifications.PasswordChangeNotificationDto;
import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NotificationService {
    void sendRegistrationEmail(RegistrationNotificationDto registrationNotificationDto);

    void sendPasswordChangeEmail(PasswordChangeNotificationDto passwordChangeNotificationDto);

    Page<NotificationDto> findAll(Pageable pageable);

    NotificationDto updateNotification(CreateNotificationDto createNotificationDto, NotificationType notificationType);
}
