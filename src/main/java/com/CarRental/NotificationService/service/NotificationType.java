package com.CarRental.NotificationService.service;

public enum NotificationType {
    REGISTRATION_NOTIFICATION(1);

    private final int value;
    private NotificationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
