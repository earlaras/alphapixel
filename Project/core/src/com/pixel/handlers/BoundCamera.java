package com.pixel.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class BoundCamera extends OrthographicCamera {
	
	private float xmin;
	private float xmax;
	private float ymin;
	private float ymax;
	
	public BoundCamera() {
		
		this(0, 0, 0, 0);
		
	}
	
	public BoundCamera(float xmin, float xmax, float ymin, float ymax) {
		
		super();
		setBounds(xmin, xmax, ymin, ymax);
		
	}
	
	public void setBounds(float xmin, float xmax, float ymin, float ymax) {
		
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	public void setPosition (float x, float y) {
		
		setPosition(x, y);
		
	}
	
}
