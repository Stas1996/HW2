package com.example.hw2;

public class topTen {
    private double lat = 0.00;
    private double lon = 0.00;
    private int numOfMoves = 0;
    private String name;

    public topTen() { }

    public topTen(String name,double lat, double lon, int numOfMoves) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.numOfMoves = numOfMoves;
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
    }

    public String getName() {
        return name;
    }

    public topTen setName(String name) {
        this.name = name;
        return this;
    }
}
