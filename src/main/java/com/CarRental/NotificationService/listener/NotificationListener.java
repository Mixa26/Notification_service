package com.CarRental.NotificationService.listener;

import com.CarRental.NotificationService.dto.notifications.CancEmailDto;
import com.CarRental.NotificationService.dto.notifications.PasswordChangeNotificationDto;
import com.CarRental.NotificationService.dto.notifications.ResEmailDto;
import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;
import com.CarRental.NotificationService.helper.MessageHelper;
import com.CarRental.NotificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
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
    @JmsListener(destination = "${destination.passwordChangeNotify}")
    public void sendPasswordChangeNotification(Message message) throws JMSException {
        PasswordChangeNotificationDto passwordChangeNotificationDto = messageHelper.getMessage(message, PasswordChangeNotificationDto.class);
        notificationService.sendPasswordChangeEmail(passwordChangeNotificationDto);
    }

    @JmsListener(destination = "${destination.notify_reservation}")
    public void sendReservationNotification(Message message) throws JMSException {
        ResEmailDto resEmailDto = messageHelper.getMessage(message, ResEmailDto.class);
        notificationService.sendResEmailDto(resEmailDto);
    }

    @JmsListener(destination = "${destination.notify_cancelRes}")
    public void sendCancelReservationNotification(Message message) throws JMSException {
        CancEmailDto cancEmailDto = messageHelper.getMessage(message, CancEmailDto.class);
        notificationService.sendCancResEmailDto(cancEmailDto);
    }
}
