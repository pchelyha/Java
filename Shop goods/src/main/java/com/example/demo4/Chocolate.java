package com.example.demo4;

public class Chocolate extends Food {
    public enum Type {white, black, milk;} // какие типы шоколада бывают
    public Type type;// а это собственно тип шоколада

    public Chocolate(int kkal, String title, String desc, Type type) {
        super(kkal, title, desc);
        this.type = type;
    }
    @Override
    public String getDescription() {
        String typeString = "";
        switch (this.type)
        {
            case white:
                typeString = "белый";
                break;
            case black:
                typeString = "черный";
                break;
            case milk:
                typeString = "молочный";
                break;
        }

        return String.format("Шоколад %s", typeString);
    }
}