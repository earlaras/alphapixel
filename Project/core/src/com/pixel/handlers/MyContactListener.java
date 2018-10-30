package com.pixel.handlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.pixel.mygame.PixelGame;

public class MyContactListener implements ContactListener{
	
	private int numFootContacts;
	private Array<Body> bodiesToRemove;
	
	public MyContactListener() {
		
		super();
		bodiesToRemove = new Array<Body>();
		
	}
	
	//CALLED WHEN TWO FIXTURES START TO COLLIDE
	public void beginContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		//TO IDENTIFY
		//System.out.println(fa.getUserData() + ", " + fb.getUserData());
		
		//FOOT SENSOR
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numFootContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts++;
		}
		
		//REMOVE CRYSTALS
		if(fa.getUserData() != null && fa.getUserData().equals("crystal")) {
			
			bodiesToRemove.add(fa.getBody());
			
		}
		if(fb.getUserData() != null && fb.getUserData().equals("crystal")) {
			
			bodiesToRemove.add(fb.getBody());
			
		}
		
		
	}

	//CALLED WHEN THE COLLISION ENDS
	public void endContact(Contact contact) {
		
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() != null && fa.getUserData().equals("foot")) {
			numFootContacts--;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("foot")) {
			numFootContacts--;
		}
		
	}
	
	public boolean isPlayerOnGround() {
		
		return numFootContacts > 0;
		
	}
	
	public Array<Body> getBodiesToRemove() {
		
		return bodiesToRemove;
		
	}
	
	//COLLISION DETECTION
	//PRESOLVE GOES HERE
	//COLLISION HANDLING
	//POSTSOLVE GOES HERE
	public void preSolve(Contact contact, Manifold oldManifold) {}

	public void postSolve(Contact contact, ContactImpulse impulse) {}
	
}
