package com.github.angryweather.smallfish.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.angryweather.smallfish.SmallFish;

public class GameScreen implements Screen {
    private final SmallFish game;
    private TextureAtlas.AtlasRegion smallFishBlue;
    TextureAtlas textureAtlas;

    public GameScreen(final SmallFish game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.manager.loadGameAssets();
        textureAtlas = game.manager.assetManager.get("assets/fish.atlas", TextureAtlas.class);
        smallFishBlue = textureAtlas.findRegion("smallFishBlue");
    }

    @Override
    public void render(float delta) {
        game.camera.update();

        ScreenUtils.clear(Color.TEAL);
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(smallFishBlue, 0, SmallFish.HEIGHT / 2f);
        game.spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("Disposing...");
        textureAtlas.dispose();
    }
}
