package com.pixel.handlers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	private TextureRegion[] frames;
	private float time;
	private float delay;
	private int currentFrame;
	private int timesPlayed;
	private int typeOfAnim = 0;
	
	public Animation() {}
	
	public Animation(TextureRegion[] frames, int type) {
		
		this(frames, 1 / 12f, 0);
		this.typeOfAnim = type;
		
	}
	
	public Animation(TextureRegion[] frames, float delay, int type) {
		
		setFrames(frames, delay, type);
		
	}
	
	public void setFrames(TextureRegion[] frames, float delay, int type) {
		
		this.frames = frames;
		this.delay = delay;
		this.typeOfAnim = type;
		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
		
	}
	
	public void update(float dt) {
		
		if (delay <= 0) { return; }
		
		time += dt;
		
		while (time >= delay) {
			step();
		}
		
	}
	
	private void step() {
		
		if (typeOfAnim == 0) {
			time -= delay;
			currentFrame++;
			if(currentFrame == frames.length) {
				currentFrame = 0;
				timesPlayed++;
			}
		
		} else if (typeOfAnim == 1) {
			time -= delay;
			if (timesPlayed >= 1) { 
				
				return; 
			}
			currentFrame++;
			if(currentFrame == frames.length) {
				currentFrame = 0;
				timesPlayed++;
			}
		}
		
	}
	
	public TextureRegion getFrame() {
		
		return frames[currentFrame];
		
	}
	
	public TextureRegion[] getFrames() {
		
		return frames;
		
	}
	
	public float getDelay() {
		
		return delay;
		
	}
	
	public int getTimesPlayed() {
		
		return timesPlayed;
		
	}
}
