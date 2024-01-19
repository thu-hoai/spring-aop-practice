package com.example.demo.java8;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        ScreenResolution resolution = new ScreenResolution(750, 1334);
        DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
        Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));

        int width = getMobileScreenWidth(Optional.of(mobile));
        System.out.println("Apple iPhone 6s Screen Width = " + width);

        Mobile mobile2 = new Mobile(2015001, "Apple", "iPhone 6s", Optional.empty());
        int width2 = getMobileScreenWidth(Optional.of(mobile2));
        System.out.println("Apple iPhone 16s Screen Width = " + width2);
    }

    public static Integer getMobileScreenWidth(Optional<Mobile> mobile) {
        return mobile.flatMap(Mobile::getDisplayFeatures)
                .flatMap(DisplayFeatures::getResolution)
                .map(ScreenResolution::getWidth).orElse(0);
    }
}

class DisplayFeatures {

    private String size; // In inches
    private Optional<ScreenResolution> resolution;

    public DisplayFeatures(String size, Optional<ScreenResolution> resolution) {
        this.size = size;
        this.resolution = resolution;
    }

    public String getSize() {
        return size;
    }

    public Optional<ScreenResolution> getResolution() {
        return resolution;
    }
}

class Mobile {

    private long id;
    private String brand;
    private String name;
    private Optional<DisplayFeatures> displayFeatures;

    public Mobile(long id, String brand, String name, Optional<DisplayFeatures> displayFeatures) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.displayFeatures = displayFeatures;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Optional<DisplayFeatures> getDisplayFeatures() {
        return displayFeatures;
    }
}

class ScreenResolution {

    private int width;
    private int height;

    public ScreenResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}