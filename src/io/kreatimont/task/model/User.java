package io.kreatimont.task.model;

import java.util.Date;

public class User {

    String name;
    String surname;
    String city;
    String country;
    String phone;
    String email;
    String role;
    Date bday;


    public User(String name, String surname, String city, String country, String phone, String email, String role) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    public User(String name, String surname, String city, String country, String phone, String email, String role, Date bday) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.bday = bday;
    }
}

