package com.github.angryweather.smallfish.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.angryweather.smallfish.SmallFish;
import com.github.angryweather.smallfish.entities.EnemyFish;
import com.github.angryweather.smallfish.entities.FishTypes;
import com.github.angryweather.smallfish.entities.Player;
import org.w3c.dom.Text;

import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    private final SmallFish game;
    private TextureRegion smallFishBlue = new TextureRegion();
    TextureAtlas textureAtlas;
    private Player player;
    private long timer = TimeUtils.nanoTime();
    private final Random random = new Random();
    private EnemyFish enemyFish;
    Array<EnemyFish> enemyFishAll = new Array<>();
    private final BitmapFont bitmapFontScore = new BitmapFont();

    public GameScreen(final SmallFish game) {
        this.game = game;
    }

    @Override
    public void show() {
        game.manager.loadGameAssets();
        textureAtlas = game.manager.assetManager.get("assets/fish.atlas", TextureAtlas.class);
        smallFishBlue = new TextureRegion(textureAtlas.findRegion(FishTypes.smallFishBlue.toString()));
        player = new Player(smallFishBlue);
        bitmapFontScore.getData().setScale(0.5f ,0.5f);


    }

    @Override
    public void render(float delta) {
        game.camera.update();
        ScreenUtils.clear(Color.TEAL);

        if (TimeUtils.nanoTime() - timer > 1000000000L) {
            timer = TimeUtils.nanoTime();
            FishTypes randomEnemy = randomFishType();
            TextureRegion enemy = new TextureRegion(textureAtlas.findRegion(randomEnemy.toString()));
            enemyFish = new EnemyFish(enemy, randomEnemy);
            enemyFishAll.add(enemyFish);
        }

        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(smallFishBlue, player.playerRect.x, player.playerRect.y);

        // draw all enemy fish on the screen
        for (Iterator<EnemyFish> it = enemyFishAll.iterator(); it.hasNext();) {
            EnemyFish enemyFish = it.next();

            if (player.playerRect.overlaps(enemyFish.enemyRect)) {
                game.setScreen(new GameOverScreen(game, player.getScore()));
            }

            if (enemyFish.enemyRect.x + enemyFish.enemyRect.width < 0) {
                player.setScore(player.getScore() + 1);
                it.remove();
            }

            game.spriteBatch.draw(enemyFish.textureRegion, enemyFish.enemyRect.x, enemyFish.enemyRect.y);
            enemyFish.move(delta);
        }

        // show score on screen
        bitmapFontScore.draw(game.spriteBatch, "Score: " + player.getScore(), 5, SmallFish.HEIGHT - 5);

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
