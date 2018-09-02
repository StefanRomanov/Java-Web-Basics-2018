package org.softuni.fdmc.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private User user;
    private Cat cat;
    private LocalDateTime madeOn;

    public Order(User user, Cat cat) {
        this.user = user;
        this.cat = cat;
        this.madeOn = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getMadeOn(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return this.madeOn.format(formatter);
    }
}
