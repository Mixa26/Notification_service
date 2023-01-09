package com.CarRental.NotificationService.service;

import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;

public interface NotificationService {
    void sendRegistrationEmail(RegistrationNotificationDto registrationNotificationDto);
}
