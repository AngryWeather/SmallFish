package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

import java.util.Random;

public class EnemyFish {
    public Rectangle enemyRect = new Rectangle();
    private Fish fish;
    public TextureRegion textureRegion;
    private Random random = new Random();

    public EnemyFish(TextureRegion textureRegion, FishTypes fishType) {
        this.textureRegion = textureRegion;
        fish = new Fish(fishType);
        this.textureRegion.flip(true, false);
        enemyRect.x = SmallFish.WIDTH;
        enemyRect.y = random.nextInt(0, SmallFish.HEIGHT - textureRegion.getRegionHeight());
        enemyRect.width = textureRegion.getRegionWidth();
        enemyRect.height = textureRegion.getRegionHeight();
    }

    public void move(float delta) {
        enemyRect.x = enemyRect.x - fish.speed * delta;
    }
}
