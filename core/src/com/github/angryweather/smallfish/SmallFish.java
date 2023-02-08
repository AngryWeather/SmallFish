package com.github.angryweather.smallfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.angryweather.smallfish.screens.GameScreen;

public class SmallFish extends Game {
	public static final float WIDTH = 171;
	public static final float HEIGHT = 96;
	public final Manager manager = new Manager();
	public OrthographicCamera camera;
	public Viewport viewport;
	public SpriteBatch spriteBatch;


	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SmallFish.WIDTH, SmallFish.HEIGHT);
		viewport = new FitViewport(SmallFish.WIDTH, SmallFish.HEIGHT, camera);
		spriteBatch = new SpriteBatch();
		this.setScreen(new GameScreen(this));

	}

	public void render() {
		super.render();
	}

	public void dispose() {
		super.dispose();
	}

}
