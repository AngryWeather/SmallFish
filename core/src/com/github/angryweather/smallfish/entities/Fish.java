package com.github.angryweather.smallfish.entities;

public class Fish {
    public int speed;
    private FishTypes fishType;

    public FishTypes getFishType() {
        return fishType;
    }

    public void setFishType(FishTypes fishType) {
        this.fishType = fishType;
    }

    public Fish(FishTypes fishType) {
        this.fishType = fishType;
        setSpeed();
    }


    public void setSpeed() {
        switch (fishType) {
            case smallFishBlue:
                speed = 20;
                break;
            case smallFishBrown:
                speed = 40;
                break;
            case smallFishRed:
                speed = 60;
                break;
            case smallFishGreen:
                speed = 80;
                break;
            case smallFishPurple:
                speed = 100;
                break;
            case smallFishYellow:
                speed = 120;
                break;
        }
    }

}
