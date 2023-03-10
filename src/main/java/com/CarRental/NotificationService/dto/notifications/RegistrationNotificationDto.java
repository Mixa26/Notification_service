package com.CarRental.NotificationService.dto.notifications;

public class RegistrationNotificationDto {

    private Long id;
    private String name;

    private String surname;

    private String email;

    private String activationLink;

    public RegistrationNotificationDto(){
    }

    public RegistrationNotificationDto(Long id, String name, String surname, String email, String activationLink) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.activationLink = activationLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActivationLink() {
        return activationLink;
    }

    public void setActivationLink(String activationLink) {
        this.activationLink = activationLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
