package com.pixel.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pixel.mygame.PixelGame;

public class HUD {
	
	private Player player;
	private TextureRegion[] nums;
	private Texture texc = PixelGame.res.getTexture("hudcr");
	private Texture texx = PixelGame.res.getTexture("x");
	
	public HUD (Player player) {
		
		Texture tex = PixelGame.res.getTexture("num");
		
		nums = new TextureRegion[14];
		
		for (int i = 0; i < nums.length; i++) {
			
			nums[i] = new TextureRegion(tex, i * 32 , 0, 32, 32);
			
		}
		
	}
	
	public void render(SpriteBatch sb, int count) {
		
		sb.begin();
		sb.draw(texc, 8, 200); 
		sb.draw(texx, 40, 200);
		sb.draw(nums[count], 72, 200);
		sb.end();
		
	}
	
}
