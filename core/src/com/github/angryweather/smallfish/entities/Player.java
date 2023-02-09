package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

public class Player {
    public final Rectangle playerRect = new Rectangle();
    private Fish fish = new Fish(FishTypes.smallFishBlue);

    // attach a rectangle to the player
    public Player(TextureRegion player) {
        playerRect.x = 0;
        playerRect.y = SmallFish.HEIGHT / 2f - (player.getRegionHeight() / 2f);
        playerRect.width = player.getRegionWidth();
        playerRect.height = player.getRegionHeight();
    }

    public void move(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            System.out.println(playerRect.y);
            playerRect.y = Math.min(playerRect.y + fish.speed * delta, SmallFish.HEIGHT - playerRect.height);
        }
        if (Gdx.input.isKeyPressed((Input.Keys.DOWN))) {
            System.out.println(playerRect.y);
            playerRect.y = Math.max(playerRect.y - fish.speed * delta, 0);
        }
    }
}
