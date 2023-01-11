package com.CarRental.NotificationService.controller;

import com.CarRental.NotificationService.dto.CreateNotificationDto;
import com.CarRental.NotificationService.dto.NotificationDto;
import com.CarRental.NotificationService.mapper.NotificationMapper;
import com.CarRental.NotificationService.security.CheckSecurity;
import com.CarRental.NotificationService.security.TokenService;
import com.CarRental.NotificationService.service.NotificationService;
import com.CarRental.NotificationService.service.NotificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    private TokenService tokenService;

    public NotificationController(NotificationService notificationService, TokenService tokenService) {
        this.notificationService = notificationService;
        this.tokenService = tokenService;
    }

    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(@RequestHeader("Authorization") String authorization, Pageable pageable)
    {
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{notificationType}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<NotificationDto> updateNotification(@RequestHeader("Authorization") String authorization, @RequestBody CreateNotificationDto notificationDto, @PathVariable String notificationType)
    {
        return new ResponseEntity<>(notificationService.updateNotification(notificationDto, NotificationType.valueOf(notificationType.toUpperCase())), HttpStatus.OK);
    }
}
