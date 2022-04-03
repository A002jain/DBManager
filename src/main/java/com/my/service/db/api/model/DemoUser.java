package com.my.service.db.api.model;

import javax.persistence.*;

@Entity
@Table
public class DemoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String firstName;

    private String lastName;

    private int age;

    public DemoUser() {}

    public DemoUser(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
