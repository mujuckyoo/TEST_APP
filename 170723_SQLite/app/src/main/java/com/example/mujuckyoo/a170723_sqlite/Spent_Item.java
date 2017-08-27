package com.example.mujuckyoo.a170723_sqlite;

/**
 * Created by mujuckyoo on 2017. 8. 20..
 */

public class Spent_Item {

    String time;
    String title;
    String writer;

    public Spent_Item(String time, String title, String writer) {
        this.time = time;
        this.title = title;
        this.writer = writer;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getWirter() {
        return writer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWirter(String writer) {
        this.writer = writer;
    }

}
