package com.pixel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.pixel.mygame.PixelGame;

public class Enemy extends B2DSprite{

	public Enemy(Body body) {
		
		super(body);
		
		Texture tex = PixelGame.res.getTexture("snakeidle");
		
		TextureRegion[] sprites = TextureRegion.split(tex, 46 , 46)[0];
		
		setAnimation(sprites, 1/6f, 0);
		
	}
}
