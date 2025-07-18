package com.example.education_management_api.model;

import java.time.LocalDate;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    private LocalDate birthday;
    private String address;

    public Person() {
    }

    public Person(String firstName, String lastName, LocalDate birthday, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public void displayName() {
        System.out.println(this.getFullName());
    }

    public void setBirthday(LocalDate date) {
        this.birthday = date;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setInternalAddress(String address) {
        this.address = address;
    }

    public void introduce() {
        System.out.println("Hello I am person 2");
    }

    public String getType() {
        return "người";
    }

    public int getId() {
        return -1;
    }

    public void displayAllInformation() {
        System.out.println("Thông tin chi tiếc của " + getType());
        System.out.println("Mã " + getType() + ": " + getId());
        System.out.println("Họ và tên: " + this.getLastName() + " " + this.getFirstName());
        System.out.println("Địa chỉ " + address);
        System.out.println("Ngày sinh: " + birthday);
    }

    public String getAddress() {
        return address;
    }

    String getTimeGoToSchool() {
        return "Person not go to school";
    }
}
