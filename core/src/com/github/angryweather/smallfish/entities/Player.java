package com.github.angryweather.smallfish.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.github.angryweather.smallfish.SmallFish;

public class Player {
    public final Rectangle playerRect = new Rectangle();

    // attach a rectangle to the player
    public Player(TextureRegion player) {
        playerRect.x = 0;
        playerRect.y = SmallFish.HEIGHT / 2f - (player.getRegionHeight() / 2f);
        playerRect.width = player.getRegionWidth();
        playerRect.height = player.getRegionHeight();
    }
}
