package com.github.angryweather.smallfish.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.angryweather.smallfish.SmallFish;
import com.github.angryweather.smallfish.entities.*;

import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    private final SmallFish game;
    TextureAtlas textureAtlas;
    private Player player;
    private long timer = TimeUtils.nanoTime();
    private final Random random = new Random();
    final Array<EnemyFish> enemyFishAll = new Array<>();
    final Array<Food> foodAll = new Array<>();
    private final BitmapFont bitmapFontScore = new BitmapFont();
    private final BitmapFont bitmapFontFood = new BitmapFont();
    private Sound foodSound;
    private Sound scoreSound;

    public GameScreen(final SmallFish game) {
        this.game = game;
    }

    @Override
    public void show() {
        foodSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/food.wav"));
        scoreSound = Gdx.audio.newSound(Gdx.files.internal("assets/sounds/score.wav"));

        game.manager.loadGameAssets();
        textureAtlas = game.manager.assetManager.get("assets/images/fish.atlas", TextureAtlas.class);
        TextureRegion smallFishBlue = new TextureRegion(textureAtlas.findRegion(FishTypes.smallFishBlue.toString()));
        player = new Player(smallFishBlue);
        bitmapFontScore.getData().setScale(0.5f ,0.5f);
        bitmapFontFood.getData().setScale(0.5f, 0.5f);
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        ScreenUtils.clear(Color.TEAL);

        if (TimeUtils.nanoTime() - timer > 1000000000L) {
            timer = TimeUtils.nanoTime();
            FishTypes randomEnemy = randomFishType();
            TextureRegion enemy = new TextureRegion(textureAtlas.findRegion(randomEnemy.toString()));
            EnemyFish enemyFish = new EnemyFish(enemy, randomEnemy);
            enemyFishAll.add(enemyFish);

            // create food on the screen
            TextureRegion foodRegion = new TextureRegion(textureAtlas.findRegion("food"));
            Food food = new Food(foodRegion);
            foodAll.add(food);
        }

        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(player.getTextureRegion(), player.playerRect.x, player.playerRect.y);

        if (player.canGetPromoted) {
            if (player.isPromoted()) {
                if (player.promotionLevel < player.maxPromotionLevel) {
                    System.out.println("promotion level: " + player.promotionLevel);
                    player.promotionLevel += 1;
                    player.setFish(new Fish(FishTypes.values()[player.promotionLevel]));
                    System.out.println(player.getFish().getFishType());
                    TextureRegion playerTextureRegion = new TextureRegion(textureAtlas.findRegion(
                            player.getFish().getFishType().toString()));
                    player.setTextureRegion(playerTextureRegion);
                    player.canGetPromoted = false;
                }
            }
        }

        // draw all enemy fish on the screen
        for (Iterator<EnemyFish> it = enemyFishAll.iterator(); it.hasNext();) {
            EnemyFish enemyFish = it.next();

            if (player.playerRect.overlaps(enemyFish.enemyRect)) {
                game.setScreen(new GameOverScreen(game, player.getScore()));
            }

            if (enemyFish.enemyRect.x + enemyFish.enemyRect.width < 0) {
                scoreSound.play();
                player.setScore(player.getScore() + 1);
                it.remove();
            }

            game.spriteBatch.draw(enemyFish.textureRegion, enemyFish.enemyRect.x, enemyFish.enemyRect.y);
            enemyFish.move(delta);
        }

        for (Iterator<Food> it = foodAll.iterator(); it.hasNext();) {
            Food food = it.next();

            if (player.playerRect.overlaps(food.foodRect)) {
                foodSound.play();
                it.remove();
                player.setFoodEaten(player.getFoodEaten() + 1);
            }

            if (food.foodRect.x + food.foodRect.width < 0) {
                it.remove();
            }

            game.spriteBatch.draw(food.textureRegion, food.foodRect.x, food.foodRect.y);
            food.move(delta);
        }

        // show score on screen
        bitmapFontScore.draw(game.spriteBatch, "Score: " + player.getScore(), 5, SmallFish.HEIGHT - 5);
        bitmapFontFood.draw(game.spriteBatch, "Food: " + player.getFoodEaten(), SmallFish.WIDTH - 30,
                SmallFish.HEIGHT - 5);

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
        bitmapFontScore.dispose();
        scoreSound.dispose();
        foodSound.dispose();
    }
}
