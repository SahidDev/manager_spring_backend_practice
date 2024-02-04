package com.sahidDev.managerSystem;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;

    public Employee(String firstName, String lastName, String employeeId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
}