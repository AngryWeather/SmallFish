package com.github.angryweather.smallfish;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.github.angryweather.smallfish.SmallFish;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.enableGLDebugOutput(true, System.out);
		config.setForegroundFPS(60);
		config.setTitle("Small Fish");
		config.setWindowedMode(1280, 720);
		new Lwjgl3Application(new SmallFish(), config);
	}
}
