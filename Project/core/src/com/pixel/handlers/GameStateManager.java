package com.pixel.handlers;

import java.util.Stack;

import com.pixel.mygame.PixelGame;
import com.pixel.states.GameState;
import com.pixel.states.Play;

public class GameStateManager {
	
	private PixelGame game;
	private Stack<GameState> gameStates;
	public static final int PLAY = 666777;
	
	public GameStateManager (PixelGame game) {
		
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
		
	}
	
	public PixelGame game() {
		
		return game;
		
	}
	
	public void update(float dt) {
		
		gameStates.peek().update(dt);
		
	}
	
	public void render() {
		
		gameStates.peek().render();
		
	}
	
	private GameState getState(int state) {
		
		if (state == PLAY) return new Play(this);
		return null;
		
	}
	
	public void setState(int state) {
		
		popState();
		pushState(state);
		
	}
	
	public void pushState(int state) {
		
		gameStates.push(getState(state));
		
	}
	
	public void popState() {
		
		GameState g = gameStates.pop();
		g.dispose();
	}
	
}
