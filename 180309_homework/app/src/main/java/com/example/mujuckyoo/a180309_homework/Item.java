package com.example.mujuckyoo.a180309_homework;

/**
 * Created by mujuckyoo on 2018. 3. 9..
 */

public class Item {
        long image;
        String name;
        String num;

        long getImage() {
            return this.image;
        }

        String getName() {
            return this.name;
        }

        String getNum() {
            return this.num;
        }

    Item() {
    }

    Item(long image, String name, String num) {
            this.image = image;
            this.name = name;
            this.num = num;
        }

    public void setImage(long image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

