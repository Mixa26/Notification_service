package com.CarRental.NotificationService.service.impl;

import com.CarRental.NotificationService.domain.Notification;
import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;
import com.CarRental.NotificationService.exceptions.NotFoundException;
import com.CarRental.NotificationService.repository.NotificationRepository;
import com.CarRental.NotificationService.service.NotificationService;
import com.CarRental.NotificationService.service.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private JavaMailSender javaMailSender;

    private NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    private String getNotificationText(NotificationType notificationType)
    {
        Notification notification =
                notificationRepository.findById((long) notificationType.getValue())
                        .orElseThrow(() -> new NotFoundException(String
                                .format("Notification with %d not found.", notificationType.getValue())));
        return notification.getMessage();
    }

    @Override
    public void sendRegistrationEmail(RegistrationNotificationDto registrationNotificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mmadzarevic5520rn@raf.rs");
        message.setTo(registrationNotificationDto.getEmail());

        String unformattedMessage = getNotificationText(NotificationType.REGISTRATION_NOTIFICATION);

        String messageText = String.format(unformattedMessage, registrationNotificationDto.getName(), registrationNotificationDto.getSurname());

        message.setText(messageText);
        message.setSubject("You have successfully registered for Car Rental.");

        javaMailSender.send(message);
    }
}
