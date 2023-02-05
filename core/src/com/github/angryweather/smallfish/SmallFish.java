package com.github.angryweather.smallfish;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SmallFish extends Game {
	public static final int WIDTH = 128;
	public static final int HEIGHT = 64;
	private final Manager manager = new Manager();
	public OrthographicCamera camera;
	public Viewport viewport;


	@Override
	public void create() {
		camera = new OrthographicCamera(SmallFish.WIDTH, SmallFish.HEIGHT);
		viewport = new StretchViewport(SmallFish.WIDTH, SmallFish.HEIGHT, camera);
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		super.dispose();
	}

}
