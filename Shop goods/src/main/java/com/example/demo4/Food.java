package com.example.demo4;

public class Food {
    public int kkal;// количество калорий
    public String title;// название
    public String desc;


    public Food(int kkal, String title, String desc) {
        this.setKkal(kkal);
        this.setTitle(title);
        this.setDesc(desc);
    }


    @Override
    public String toString() {
        return String.format("%s: %s ккал", this.getTitle(), this.getKkal());
    }

    public int getKkal() {
        return kkal;
    }

    public void setKkal(int kkal) {
        this.kkal = kkal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return "";
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }
}