package org.softuni.fdmc.data;

public class Cat {
    private String name;
    private String breed;
    private String color;
    private int legs;
    private User creator;
    private int views;

    public Cat(String name, String breed, String color, int legs, User creator) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.legs = legs;
        this.creator = creator;
        this.views = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
