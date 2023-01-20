package com.CarRental.NotificationService.dto.notifications;

public class ResEmailDto {

    private Long clientId;

    private String email;
    private Long resId;

    public ResEmailDto(Long clientId, String email, Long resId) {
        this.clientId = clientId;
        this.email = email;
        this.resId = resId;
    }

    public ResEmailDto() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long id) {
        this.clientId = id;
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
