package com.pixel.mygame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixel.mygame.PixelGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = PixelGame.TITLE;
		config.width = PixelGame.vWIDTH * PixelGame.SCALE;
		config.height = PixelGame.vHEIGHT * PixelGame.SCALE;
		
		new LwjglApplication(new PixelGame(), config);
		
		
	}
}
