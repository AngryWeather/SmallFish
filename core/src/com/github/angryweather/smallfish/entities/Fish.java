package com.github.angryweather.smallfish.entities;

public class Fish {
    public int speed;
    private final FishTypes fishType;

    public Fish(FishTypes fishType) {
        this.fishType = fishType;
        setSpeed();
    }

    public void setSpeed() {
        switch (fishType) {
            case smallFishBlue:
                speed = 10;
        }
    }

}
