package com.acme.utils;

public class MyDate {
    private int day;
    private int year;
    private int month;
    private static MyDate[] holidays;

    static {
        holidays = new MyDate[6];
        holidays[0] = new MyDate(1, 1, 2016);
        holidays[1] = new MyDate(9, 5, 2016);
        holidays[2] = new MyDate(5, 30, 2016);
        holidays[3] = new MyDate(10, 24, 2016);
        holidays[4] = new MyDate(7, 4, 2016);
        holidays[5] = new MyDate(12, 25, 2016);
    }

    public MyDate() { this(1, 1, 1900); }

    public MyDate(int m, int d, int y) {
        if (valid(m, d, y)) {
            month = m;
            day = d;
            year = y;
        }
    }

    public static MyDate[] getHolidays() { return holidays; }

    public static void listHolidays() {
        System.out.println("the holidays are:");
        for (var holiday : holidays) {
            System.out.println(holiday);
        }
    }

    public int getMonth() { return month; }

    public void setMonth(int month) {
        if (valid(month, day, year)) {
            this.month = month;
        }
    }

    public int getYear() { return year; }

    public void setYear(int year) {

        if (valid(month, day, year)) {
            this.year = year;
        }
    }

    public int getDay() { return day; }

    public void setDay(int day) {
        if (valid(month, day, year)) {
            this.day = day;
        }
    }

    private boolean valid(int month, int day, int year) {
        if (day > 31 || day < 1 || month > 12 || month < 1) {

            System.out.println("Attempting to create a non-valid date " +
                               month + "/" + day + "/" + year);
            return false;
        }
        switch (month) {
        case 4:
        case 6:
        case 9:
        case 11:
            return (day <= 30);
        case 2:
            return day <= 28 || (day == 29 && year % 4 == 0);
        }
        return true;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    public void setDate(int m, int d, int y) {
        if (valid(m, d, y)) {
            month = m;
            day = d;
            year = y;
        }
    }

    public static void leapYears() {
        for (int i = 1752; i <= 2020; i++) {
            if ((i % 4 == 0) && ((i % 100 != 0) || (i % 400 == 0)))
                System.out.println("The year " + i + " is a leap year");
        }
    }

    public boolean equals(Object o) {
        if (o instanceof MyDate) {
            MyDate d = (MyDate)o;
            if ((d.day == day) && (d.month == month) && (d.year == year)) {
                return true;
            }
        }
        return false;
    }
}
