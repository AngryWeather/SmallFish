package com.github.angryweather.smallfish;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Manager {
    public final AssetManager assetManager = new AssetManager();

    public void loadGameAssets() {
//        assetManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(new InternalFileHandleResolver()));
        assetManager.load("images/fish.atlas", TextureAtlas.class);
        assetManager.finishLoading();
    }
}
