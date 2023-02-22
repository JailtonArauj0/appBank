package com.appbank.models;

import java.sql.Date;

public class ModelClient {
    private Long id;
    private Long id_register;

    private String firstName;
    private String fullName;
    private Date birthDate;
    private String phone;

    public Long getId_register() {
        return id_register;
    }

    public void setId_register(Long id_register) {
        this.id_register = id_register;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
