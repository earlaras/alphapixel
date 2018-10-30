package com.pixel.mygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pixel.handlers.Content;
import com.pixel.handlers.GameStateManager;
import com.pixel.handlers.MyInput;
import com.pixel.handlers.MyInputProcessor;

public class PixelGame extends ApplicationAdapter {
	
	public static final String TITLE = "Pixel";
	public static final int vWIDTH = 455;
	public static final int vHEIGHT = 256;
	public static final int SCALE = 3;
	public static final float STEP = 1/60f;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;	
	private GameStateManager gsm;
	
	public static Content res;
	
	@Override
	public void create () {
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content();
		res.loadTexture("res/runningSheet.png", "runr");
		res.loadTexture("res/runningSheet2.png", "runl");
		res.loadTexture("res/idle.png", "idle");
		res.loadTexture("res/idle2.png", "idle2");
		res.loadTexture("res/crystal.png", "crystal");
		res.loadTexture("res/images/junglee.png", "background");
		res.loadTexture("res/images/num.png", "num");
		res.loadTexture("res/images/x.png", "x");
		res.loadTexture("res/images/hudcr.png", "hudcr");
		res.loadTexture("res/attack.png", "a1");
		res.loadTexture("res/attack2.png", "a2");
		res.loadTexture("res/jumpr.png", "jumpr");
		res.loadTexture("res/jumpl.png", "jumpl");
		res.loadTexture("res/falll.png", "falll");
		res.loadTexture("res/fallr.png", "fallr");
		
		res.loadSound("res/bgm/Skyliner.mp3", "theme");
		res.loadSound("res/bgm/crystal.mp3", "crystal");
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, vWIDTH, vHEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, vWIDTH, vHEIGHT);
		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		
		Gdx.graphics.setTitle(TITLE + "-- FPS: " + Gdx.graphics.getFramesPerSecond());

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		MyInput.update();
		
	}
	
	@Override
	public void dispose () {

	}
	
	public void resize (int w, int h) {
		
	}
	
	public void pause () {
		
	}
	
	public void resume () {
		
	}
	
	public SpriteBatch getSb() {
		return sb;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public OrthographicCamera getHudCam() {
		return hudCam;
	}
	
}
