package com.CarRental.NotificationService.listener;

import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;
import com.CarRental.NotificationService.helper.MessageHelper;
import com.CarRental.NotificationService.service.NotificationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class NotificationListener {
    private MessageHelper messageHelper;

    private NotificationService notificationService;

    public NotificationListener(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
    }

    @JmsListener(destination = "${destination.notify}")
    public void sendNotification(Message message) throws JMSException {
        RegistrationNotificationDto registrationNotificationDto = messageHelper.getMessage(message, RegistrationNotificationDto.class);
        notificationService.sendRegistrationEmail(registrationNotificationDto);
    }
}
