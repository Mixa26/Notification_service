package com.CarRental.NotificationService.mapper;

import com.CarRental.NotificationService.domain.Notification;
import com.CarRental.NotificationService.dto.CreateNotificationDto;
import com.CarRental.NotificationService.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public Notification notificationDtoToNotification(CreateNotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setId(notificationDto.getId());
        notification.setMessage(notificationDto.getMessage());
        return notification;
    }

    public NotificationDto notificationToNotificationDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setMessage(notification.getMessage());
        return notificationDto;
    }
}
