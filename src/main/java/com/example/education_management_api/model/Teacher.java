package com.example.education_management_api.model;

import java.time.LocalDate;

public class Teacher extends Person {
    private int teacherId;
    private int salary;

    public Teacher() {

    }

    public Teacher(String firstName) {
        this.firstName = firstName;
    }

    public Teacher(int teacherId, String firstName, String lastName, LocalDate birthday, String address) {
        super(firstName, lastName, birthday, address);
        this.teacherId = teacherId;
        this.salary = salary;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void introduce() {
        System.out.println("I'm a teacher");
    }

    @Override
    public String getType() {
        return "giảng viên";
    }

    @Override
    public int getId() {
        return teacherId;
    }

    public void displayAllInformation() {
        super.displayAllInformation();
        System.out.println("Lương: " + this.getSalary());
    }

    public void updateAddress(String address) {

    }

    @Override
    public void updateNewAddress(String address) {
        setInternalAddress(address);
    }

    public void updateRank(double score) {}
}
