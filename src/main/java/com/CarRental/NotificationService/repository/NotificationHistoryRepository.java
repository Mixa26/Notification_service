package com.CarRental.NotificationService.repository;

import com.CarRental.NotificationService.domain.NotificationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long> {

    public Page<NotificationHistory> findByFromEmail(String toEmail, Pageable pageable);

    public Page<NotificationHistory> findByToEmail(String toEmail, Pageable pageable);

    public Page<NotificationHistory> findByNotificationType(Integer notificationType, Pageable pageable);

    public Page<NotificationHistory> findByUserId(Long userId, Pageable pageable);
}
