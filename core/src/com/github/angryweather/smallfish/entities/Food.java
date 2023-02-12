package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

import java.util.Random;

public class Food {
    public final TextureRegion textureRegion;
    public final Rectangle foodRect = new Rectangle();

    public Food(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
        foodRect.x = SmallFish.WIDTH;
        Random random = new Random();
        foodRect.y = random.nextInt(SmallFish.HEIGHT - textureRegion.getRegionHeight());
        foodRect.width = textureRegion.getRegionWidth();
        foodRect.height = textureRegion.getRegionHeight();
    }

    public void move(float delta) {
        int speed = 50;
        foodRect.x = foodRect.x - speed * delta;
    }
}
