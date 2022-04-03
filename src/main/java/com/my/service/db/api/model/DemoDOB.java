package com.my.service.db.api.model;

import javax.persistence.*;

@Entity
@Table
public class DemoDOB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private int day;

    private int month;

    private int year;

    public DemoDOB(){}

    public DemoDOB( int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "DemoDOB{" +
                "id='" + id + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
