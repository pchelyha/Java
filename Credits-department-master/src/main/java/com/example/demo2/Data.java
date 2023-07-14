package com.example.demo2;

public class Data {
    private String FIO;
    private String histori;

    public Data(String FIO, String histori) {
        this.FIO=FIO;
        this.histori=histori;
    }
    public Data() {
    }
    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getHistori() {
        return histori;
    }

    public void setHistori(String histori) {
        this.histori = histori;
    }
}
