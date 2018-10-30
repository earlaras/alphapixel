package com.pixel.entities;

import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.pixel.handlers.MyInput;
import com.pixel.mygame.PixelGame;

public class Player extends B2DSprite{
	
	private int numCrystals = 0;
	private int totalCrystals;
	
	public Player (Body body) {
		
		super (body);
		
			Texture tex = PixelGame.res.getTexture("idle");
			
			TextureRegion[] sprites = TextureRegion.split(tex, 46 , 46)[0];
			
			setAnimation(sprites, 1/12f, 0);	
					
	}
	
	public void collectCrystal() { numCrystals++; }
	
	public int getNumCrystals() { return numCrystals; }
	
	public void setTotalCrystals (int i) { totalCrystals = i; }
	
	public int getTotalCrystals() { return totalCrystals; }

}
