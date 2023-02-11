package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

import java.util.Random;

public class Food {
    public TextureRegion textureRegion;
    private Random random = new Random();
    public Rectangle foodRect = new Rectangle();
    private int speed = 50;

    public Food(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        foodRect.x = SmallFish.WIDTH;
        foodRect.y = random.nextInt(SmallFish.HEIGHT - textureRegion.getRegionHeight());
        foodRect.width = textureRegion.getRegionWidth();
        foodRect.height = textureRegion.getRegionHeight();
    }

    public void move(float delta) {
        foodRect.x = foodRect.x - speed * delta;
    }
}
