package com.example.todolist;

/**
 * Created by 재윤 on 2017-03-08.
 */

public class ListData {
    String day;
    String count;

    public String getCount() {
        return count;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ListData(String day, String count) {
        this.day = day;
        this.count = count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDay() {
        return day;
    }
}
