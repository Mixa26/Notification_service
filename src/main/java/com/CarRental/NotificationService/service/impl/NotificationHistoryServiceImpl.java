package com.CarRental.NotificationService.service.impl;

import com.CarRental.NotificationService.domain.NotificationHistory;
import com.CarRental.NotificationService.dto.CreateNotificationHistoryDto;
import com.CarRental.NotificationService.dto.NotificationDto;
import com.CarRental.NotificationService.mapper.NotificationHistoryMapper;
import com.CarRental.NotificationService.repository.NotificationHistoryRepository;
import com.CarRental.NotificationService.service.NotificationHistorySevice;
import com.CarRental.NotificationService.service.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationHistoryServiceImpl implements NotificationHistorySevice {

    private NotificationHistoryRepository notificationHistoryRepository;

    private NotificationHistoryMapper notificationHistoryMapper;

    public NotificationHistoryServiceImpl(NotificationHistoryRepository notificationHistoryRepository, NotificationHistoryMapper notificationHistoryMapper) {
        this.notificationHistoryRepository = notificationHistoryRepository;
        this.notificationHistoryMapper = notificationHistoryMapper;
    }

    @Override
    public Page<CreateNotificationHistoryDto> findAll(Pageable pageable) {
        return notificationHistoryRepository.findAll(pageable).map(notificationHistoryMapper::notificationHistoryToNotificationHistoryDto);
    }

    @Override
    public Page<CreateNotificationHistoryDto> findByToEmail(String toEmail, Pageable pageable) {
        return notificationHistoryRepository.findByToEmail(toEmail, pageable).map(notificationHistoryMapper::notificationHistoryToNotificationHistoryDto);
    }

    @Override
    public Page<CreateNotificationHistoryDto> findByFromEmail(String fromEmail, Pageable pageable) {
        return notificationHistoryRepository.findByFromEmail(fromEmail, pageable).map(notificationHistoryMapper::notificationHistoryToNotificationHistoryDto);
    }

    @Override
    public Page<CreateNotificationHistoryDto> findByNotificationType(NotificationType notificationType, Pageable pageable) {
        return notificationHistoryRepository.findByNotificationType(notificationType.getValue(), pageable).map(notificationHistoryMapper::notificationHistoryToNotificationHistoryDto);
    }

    @Override
    public Page<CreateNotificationHistoryDto> findByUserId(Long userId, Pageable pageable) {
        return notificationHistoryRepository.findByUserId(userId, pageable).map(notificationHistoryMapper::notificationHistoryToNotificationHistoryDto);
    }
}
