package com.CarRental.NotificationService.mapper;

import com.CarRental.NotificationService.domain.NotificationHistory;
import com.CarRental.NotificationService.dto.CreateNotificationHistoryDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationHistoryMapper {

    public NotificationHistory notificationHistoryDtoToNotificationHistory(CreateNotificationHistoryDto notificationHistoryDto)
    {
        NotificationHistory notificationHistory = new NotificationHistory();
        notificationHistory.setFromEmail(notificationHistoryDto.getFromEmail());
        notificationHistory.setToEmail(notificationHistoryDto.getToEmail());
        notificationHistory.setSubject(notificationHistoryDto.getSubject());
        notificationHistory.setMessage(notificationHistoryDto.getMessage());
        notificationHistory.setNotificationType(notificationHistoryDto.getNotificationType());
        notificationHistory.setDateTime(notificationHistoryDto.getDateTime());

        return notificationHistory;
    }

    public CreateNotificationHistoryDto notificationHistoryToNotificationHistoryDto(NotificationHistory notificationHistory)
    {
        CreateNotificationHistoryDto notificationHistoryDto = new CreateNotificationHistoryDto();
        notificationHistoryDto.setFromEmail(notificationHistory.getFromEmail());
        notificationHistoryDto.setToEmail(notificationHistory.getToEmail());
        notificationHistoryDto.setSubject(notificationHistory.getSubject());
        notificationHistoryDto.setMessage(notificationHistory.getMessage());
        notificationHistoryDto.setNotificationType(notificationHistory.getNotificationType());
        notificationHistoryDto.setDateTime(notificationHistory.getDateTime());

        return notificationHistoryDto;
    }
}
