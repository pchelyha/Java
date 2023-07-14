package com.example.demo4;

public class Fruit extends Food {
    public Boolean isFresh;// свежий ли фрукт

    public Fruit(int kkal, String title, String desc, Boolean isFresh) {
        super(kkal, title, desc);
        this.isFresh = isFresh;
    }
    @Override
    public String getDescription() {
        String isFreshString = this.isFresh ? "свежий" : "не свежий";
        return String.format("Фрукт %s", isFreshString);
    }
}