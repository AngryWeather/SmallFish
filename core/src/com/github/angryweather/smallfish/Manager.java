package com.github.angryweather.smallfish;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Manager {
    private final AssetManager assetManager = new AssetManager();

    private void loadGameAssets() {
        assetManager.load("smallFishBlue.png", Texture.class);
        assetManager.finishLoading();
    }
}
