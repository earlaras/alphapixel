package com.pixel.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {
	
	private HashMap<String, Texture> textures;
	private HashMap<String, Sound> sounds;
	
	public Content () {
		
		textures = new HashMap<String, Texture>();
		sounds = new HashMap<String, Sound>();
		
	}
	
	public void loadSound(String path, String key) {
		
		Sound sd = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(key, sd);
		
	}
	public void loadTexture(String path, String key) {
		
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
		
	}
	
	public Sound getSound(String key) {
		
		return sounds.get(key);
		
	}
	
	public Texture getTexture(String key) {
		
		return textures.get(key);
		
	}
	
	public void disposeSound(String key) {
		
		Sound sd = sounds.get(key);
		
		if (sd !=null) { sd.dispose(); }
		
	}
	
	public void disposeTexture(String key) {
		
		Texture tex = textures.get(key);
		
		if(tex != null) { tex.dispose(); }
		
	}
	
	
}
