package com.github.angryweather.smallfish.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.github.angryweather.smallfish.SmallFish;

public class GameOverScreen implements Screen {
    private final SmallFish game;
    private final GlyphLayout glyphLayoutLost = new GlyphLayout();
    private final GlyphLayout glyphLayoutScore = new GlyphLayout();
    private final BitmapFont bitmapFontYouLost = new BitmapFont();
    private final BitmapFont bitmapFontScore = new BitmapFont();
    private final GlyphLayout glyphLayoutAgain = new GlyphLayout();
    private final BitmapFont bitmapFontAgain = new BitmapFont();
    private final int score;


    public GameOverScreen(final SmallFish game, int score) {
        this.score = score;
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });

        glyphLayoutLost.setText(bitmapFontYouLost, "You Lost!");
        glyphLayoutScore.setText(bitmapFontScore, "Your score: " + score);
        glyphLayoutAgain.setText(bitmapFontAgain, "Press enter to try again");
        float bitmapFontScaleFactor = 0.7f;
        bitmapFontYouLost.getData().setScale(bitmapFontScaleFactor);
        bitmapFontScore.getData().setScale(bitmapFontScaleFactor);
        bitmapFontAgain.getData().setScale(0.7f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.TEAL);
        game.spriteBatch.begin();
        bitmapFontYouLost.draw(game.spriteBatch, glyphLayoutLost, SmallFish.WIDTH / 2f - glyphLayoutLost.width / 2,
                SmallFish.HEIGHT / 2f + glyphLayoutLost.height);
        bitmapFontScore.draw(game.spriteBatch, glyphLayoutScore, SmallFish.WIDTH / 2f - glyphLayoutScore.width / 2,
                SmallFish.HEIGHT / 2f - bitmapFontYouLost.getScaleY() - 10);
        bitmapFontAgain.draw(game.spriteBatch, glyphLayoutAgain, SmallFish.WIDTH / 2f - glyphLayoutAgain.width / 2,
                SmallFish.HEIGHT / 2f - bitmapFontScore.getScaleY() - 30);
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
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
        bitmapFontScore.dispose();
        bitmapFontYouLost.dispose();
        bitmapFontAgain.dispose();
    }
}
