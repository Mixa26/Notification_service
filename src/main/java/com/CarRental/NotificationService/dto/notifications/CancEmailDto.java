package com.CarRental.NotificationService.dto.notifications;

public class CancEmailDto {

    private Long clientId;

    private String email;
    private Long resId;

    public CancEmailDto(Long clientId, String email, Long resId) {
        this.clientId = clientId;
        this.email = email;
        this.resId = resId;
    }

    public CancEmailDto() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }
}
