package com.CarRental.NotificationService.controller;

import com.CarRental.NotificationService.dto.CreateNotificationHistoryDto;
import com.CarRental.NotificationService.security.CheckSecurity;
import com.CarRental.NotificationService.security.TokenService;
import com.CarRental.NotificationService.service.NotificationHistorySevice;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/notificationHistory")
public class NotificationHistoryController {

    private NotificationHistorySevice notificationHistorySevice;

    private TokenService tokenService;

    public NotificationHistoryController(NotificationHistorySevice notificationHistorySevice, TokenService tokenService) {
        this.notificationHistorySevice = notificationHistorySevice;
        this.tokenService = tokenService;
    }

    @GetMapping("/all")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<CreateNotificationHistoryDto>> getAllNotifications(@RequestHeader("Authorization") String authorization, Pageable pageable)
    {
        return new ResponseEntity<>(notificationHistorySevice.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/received")
    @CheckSecurity(roles = {"ROLE_CLIENT", "ROLE_MANAGER"})
    public ResponseEntity<Page<CreateNotificationHistoryDto>> getAllNotificationsForUser(@RequestHeader("Authorization") String authorization, Pageable pageable)
    {
        String token = null;
        if (authorization.toString().startsWith("Bearer")) {
            //Get token
            token = authorization.toString().split(" ")[1];
        }
        Claims claims = tokenService.parseToken(token);

        return new ResponseEntity<>(notificationHistorySevice.findByUserId((long)claims.get("id", Integer.class), pageable), HttpStatus.OK);
    }
}
