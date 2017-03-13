package com.example.todolist;

/**
 * Created by 재윤 on 2017-03-13.
 */

public class DetailData {

    String title;
    String content;
    String time ;
    String plan;

    public DetailData(String title, String plan, String time, String content) {
        this.title = title;
        this.plan = plan;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getPlan() {
        return plan;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
