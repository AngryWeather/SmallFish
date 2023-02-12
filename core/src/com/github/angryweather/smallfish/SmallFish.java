package com.github.angryweather.smallfish;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.angryweather.smallfish.screens.GameScreen;

public class SmallFish extends Game {
	public static final int WIDTH = 171;
	public static final int HEIGHT = 96;
	public final Manager manager = new Manager();
	public OrthographicCamera camera;
	public Viewport viewport;
	public SpriteBatch spriteBatch;


	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_INFO);
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
		manager.assetManager.dispose();
	}

}
