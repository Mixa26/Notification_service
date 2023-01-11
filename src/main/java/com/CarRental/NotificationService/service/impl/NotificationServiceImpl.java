package com.CarRental.NotificationService.service.impl;

import com.CarRental.NotificationService.domain.Notification;
import com.CarRental.NotificationService.domain.NotificationHistory;
import com.CarRental.NotificationService.dto.CreateNotificationDto;
import com.CarRental.NotificationService.dto.NotificationDto;
import com.CarRental.NotificationService.dto.notifications.PasswordChangeNotificationDto;
import com.CarRental.NotificationService.dto.notifications.RegistrationNotificationDto;
import com.CarRental.NotificationService.exceptions.NotFoundException;
import com.CarRental.NotificationService.mapper.NotificationMapper;
import com.CarRental.NotificationService.repository.NotificationHistoryRepository;
import com.CarRental.NotificationService.repository.NotificationRepository;
import com.CarRental.NotificationService.service.NotificationService;
import com.CarRental.NotificationService.service.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private JavaMailSender javaMailSender;

    private NotificationRepository notificationRepository;

    private NotificationHistoryRepository notificationHistoryRepository;

    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationHistoryRepository notificationHistoryRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationHistoryRepository = notificationHistoryRepository;
        this.notificationMapper = notificationMapper;
    }

    private String[] getNotificationText(NotificationType notificationType)
    {
        Notification notification =
                notificationRepository.findById((long) notificationType.getValue())
                        .orElseThrow(() -> new NotFoundException(String
                                .format("Notification with %d not found.", notificationType.getValue())));
        return new String[]{notification.getSubject(),notification.getMessage()};
    }

    @Override
    public void sendRegistrationEmail(RegistrationNotificationDto registrationNotificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mmadzarevic5520rn@raf.rs");
        message.setTo(registrationNotificationDto.getEmail());

        String[] unformattedEmail = getNotificationText(NotificationType.REGISTRATION_NOTIFICATION);
        String subject = unformattedEmail[0];
        String unformattedMessage = unformattedEmail[1];

        String messageText = String.format(unformattedMessage, registrationNotificationDto.getName(), registrationNotificationDto.getSurname(), registrationNotificationDto.getActivationLink());

        message.setText(messageText);
        //"Activate your account."
        message.setSubject(subject);

        javaMailSender.send(message);

        NotificationHistory notificationHistoryEntry = new NotificationHistory();
        notificationHistoryEntry.setToEmail(registrationNotificationDto.getEmail());
        notificationHistoryEntry.setFromEmail("mmadzarevic5520rn@raf.rs");
        notificationHistoryEntry.setUserId(registrationNotificationDto.getId());
        notificationHistoryEntry.setNotificationType(NotificationType.REGISTRATION_NOTIFICATION.getValue());
        notificationHistoryEntry.setDateTime(LocalDateTime.now());
        notificationHistoryEntry.setSubject(subject);
        notificationHistoryEntry.setMessage(messageText);

        notificationHistoryRepository.save(notificationHistoryEntry);
    }

    @Override
    public void sendPasswordChangeEmail(PasswordChangeNotificationDto passwordChangeNotificationDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mmadzarevic5520rn@raf.rs");
        message.setTo(passwordChangeNotificationDto.getEmail());

        String[] unformattedEmail = getNotificationText(NotificationType.PASSWORD_CHANGE_NOTIFICATION);
        String subject = unformattedEmail[0];
        String unformattedMessage = unformattedEmail[1];

        String messageText = String.format(unformattedMessage, passwordChangeNotificationDto.getName(), passwordChangeNotificationDto.getSurname(), passwordChangeNotificationDto.getUsername());

        message.setText(messageText);
        //"Password changed."
        message.setSubject(subject);

        javaMailSender.send(message);

        NotificationHistory notificationHistoryEntry = new NotificationHistory();
        notificationHistoryEntry.setToEmail(passwordChangeNotificationDto.getEmail());
        notificationHistoryEntry.setFromEmail("mmadzarevic5520rn@raf.rs");
        notificationHistoryEntry.setUserId(passwordChangeNotificationDto.getId());
        notificationHistoryEntry.setNotificationType(NotificationType.PASSWORD_CHANGE_NOTIFICATION.getValue());
        notificationHistoryEntry.setDateTime(LocalDateTime.now());
        notificationHistoryEntry.setSubject(subject);
        notificationHistoryEntry.setMessage(messageText);

        notificationHistoryRepository.save(notificationHistoryEntry);
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public NotificationDto updateNotification(CreateNotificationDto createNotificationDto, NotificationType notificationType) {
        Notification notification = notificationRepository.findById((long)notificationType.getValue())
                .orElseThrow(() -> new NotFoundException("Notification of provided type doesn't exist"));

        notification.setSubject(createNotificationDto.getSubject());
        notification.setMessage(createNotificationDto.getMessage());
        notificationRepository.save(notification);

        return notificationMapper.notificationToNotificationDto(notification);
    }
}
