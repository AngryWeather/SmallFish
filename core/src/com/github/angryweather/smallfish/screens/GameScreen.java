package com.github.angryweather.smallfish.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.angryweather.smallfish.SmallFish;
import com.github.angryweather.smallfish.entities.EnemyFish;
import com.github.angryweather.smallfish.entities.FishTypes;
import com.github.angryweather.smallfish.entities.Player;

import java.util.Random;

public class GameScreen implements Screen {
    private final SmallFish game;
    private TextureAtlas.AtlasRegion smallFishBlue;
    TextureAtlas textureAtlas;
    private Player player;
    private long timer = TimeUtils.nanoTime();
    private final Random random = new Random();
    private TextureRegion enemy;
    Array<EnemyFish> enemyFishAll = new Array<>();

    public GameScreen(final SmallFish game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.manager.loadGameAssets();
        textureAtlas = game.manager.assetManager.get("assets/fish.atlas", TextureAtlas.class);
        smallFishBlue = textureAtlas.findRegion(FishTypes.smallFishBlue.toString());
        player = new Player(smallFishBlue);


    }

    @Override
    public void render(float delta) {
        game.camera.update();
//        System.out.println(random.nextInt(FishTypes.values().length));

        ScreenUtils.clear(Color.TEAL);

        if (TimeUtils.nanoTime() - timer > 1000000000L) {
            timer = TimeUtils.nanoTime();
            FishTypes randomEnemy = randomFishType();
            enemy = textureAtlas.findRegion(randomEnemy.toString());
            EnemyFish enemyFish = new EnemyFish(enemy, randomEnemy);
            enemyFishAll.add(enemyFish);
        }

        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(smallFishBlue, player.playerRect.x, player.playerRect.y);

        // draw all enemy fish on the screen
        for (EnemyFish enemyFish : enemyFishAll) {
            game.spriteBatch.draw(enemyFish.textureRegion, enemyFish.enemyRect.x, enemyFish.enemyRect.y);
            enemyFish.move(delta);
        }

        player.move(delta);
        game.spriteBatch.end();

    }

    private FishTypes randomFishType() {
        return FishTypes.values()[random.nextInt(FishTypes.values().length)];
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
        game.manager.assetManager.dispose();
    }
}
