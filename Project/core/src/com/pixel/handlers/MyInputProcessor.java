package com.pixel.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class MyInputProcessor extends InputAdapter{
	
	public boolean keyDown(int k) {
		
		if (k == Keys.W || k == Keys.SPACE) {
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if (k == Keys.A) {
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		if (k == Keys.D) {
			MyInput.setKey(MyInput.BUTTON3, true);
		}
		if (k == Keys.K) {
			MyInput.setKey(MyInput.BUTTON4, true);
		}
		if (k == Keys.L) {
			MyInput.setKey(MyInput.BUTTON5, true);
		}
		
		return true;
		
	}
	
	public boolean keyUp(int k) {
		
		if (k == Keys.W || k == Keys.SPACE) {
			MyInput.setKey(MyInput.BUTTON1, false);
		}
		if (k == Keys.A) {
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		if (k == Keys.D) {
			MyInput.setKey(MyInput.BUTTON3, false);
		}
		if (k == Keys.K) {
			MyInput.setKey(MyInput.BUTTON4, false);
		}
		if (k == Keys.L) {
			MyInput.setKey(MyInput.BUTTON5, false);
		}
		
		return true;
		
	}
	

}
