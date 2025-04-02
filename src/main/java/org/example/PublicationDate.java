package org.example;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;

public class PublicationDate implements Serializable {
    private int day;
    private int month;
    private int year;

    public PublicationDate(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date: " + day + "/" + month + "/" + year);
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isValidDate(int day, int month, int year) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (!isValidDate(day, this.month, this.year)) {
            throw new IllegalArgumentException("Invalid day for current month/year.");
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (!isValidDate(this.day, month, this.year)) {
            throw new IllegalArgumentException("Invalid month for current day/year.");
        }
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (!isValidDate(this.day, this.month, year)) {
            throw new IllegalArgumentException("Invalid year for current day/month.");
        }
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}