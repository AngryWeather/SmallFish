package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

public class Player {
    private TextureRegion textureRegion;
    public final Rectangle playerRect = new Rectangle();
    private Fish fish = new Fish(FishTypes.smallFishBlue);
    private int score = 0;
    private int foodEaten = 0;
    public int maxPromotionLevel = FishTypes.values().length - 1;
    public int promotionLevel = 0;
    public boolean canGetPromoted = false;

    // attach a rectangle to the player
    public Player(TextureRegion player) {
        this.textureRegion = player;
        playerRect.x = 0;
        playerRect.y = SmallFish.HEIGHT / 2f - (player.getRegionHeight() / 2f);
        playerRect.width = player.getRegionWidth() - 2;
        playerRect.height = player.getRegionHeight() - 5;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public int getFoodEaten() {
        return foodEaten;
    }

    public void setFoodEaten(int foodEaten) {
        canGetPromoted = foodEaten > this.foodEaten;
        this.foodEaten = foodEaten;
    }

    public int getFishTypeOrdinal() {
        return fish.getFishType().ordinal();
    }

    public void move(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            playerRect.y = Math.min(playerRect.y + fish.speed * delta, SmallFish.HEIGHT - playerRect.height);
        }
        if (Gdx.input.isKeyPressed((Input.Keys.DOWN))) {
            playerRect.y = Math.max(playerRect.y - fish.speed * delta, 0);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score can't be zero");
        }
        this.score = score;
    }

    public boolean isPromoted() {
        return getFoodEaten() % 10 == 0 && getFoodEaten() != 0;
    }

}
