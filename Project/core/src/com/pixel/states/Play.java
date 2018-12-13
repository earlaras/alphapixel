package com.pixel.states;

import static com.pixel.handlers.B2DVars.PPM;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.SteerableAdapter;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.pixel.entities.Crystal;
import com.pixel.entities.Enemy;
import com.pixel.entities.HUD;
import com.pixel.entities.Player;
import com.pixel.handlers.Animation;
import com.pixel.handlers.B2DVars;
import com.pixel.handlers.GameStateManager;
import com.pixel.handlers.MyContactListener;
import com.pixel.handlers.MyInput;
import com.pixel.handlers.MyInputProcessor;
import com.pixel.mygame.PixelGame;

public class Play extends GameState {
	
	private boolean debug = false;
	
	// 1: Create world 2: Define body 3: Create body 4: Define fixture 5: Create fixture
	
	private World world;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera b2dCam;
	private MyContactListener cl;
	
	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Player player;
	private Array<Crystal> crystals;
	private HUD hud;
	private Enemy snake;
	
	public Texture tex;
	public int anim;
	public Sound sd;
	public Sprite spr;
	
	private float timeAttack;
	
	public Play (GameStateManager gsm) {
		
		super(gsm);
		
		//SET UP BOX2D STUFF
		
		world = new World (new Vector2(0, -9.81f), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();
		
		//CREATE PLAYER
		
		createPlayer();
		
		//CREATE TILES
		
		createTiles();
		
		//PLAY MUSIC
		
		playMusic();
		
		//CREATE BACKGROUND
		createBackground();
		
		//CREATE CRYSTALS
		
		createCrystals();
		
		//CREATE ENEMY
		
		createEnemy();
		
		//SET A BOX2D CAM
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, PixelGame.vWIDTH/PPM, PixelGame.vHEIGHT/PPM);
		
		//SET UP HUD
		hud = new HUD(player);
				
	}
		
	public void handleInput() {
		

		
		
		//STILL
		if (cl.isPlayerOnGround()) {
			
			
			
			
		}
		
		if (!MyInput.isDown(MyInput.BUTTON4) && !MyInput.isDown(MyInput.BUTTON3) && !MyInput.isDown(MyInput.BUTTON2) && !MyInput.isDown(MyInput.BUTTON1)) {
			
			player.getBody().setLinearVelocity(0f, player.getBody().getLinearVelocity().y);
			
			if (anim == 2 || anim == 8 || anim == 4) {
				
				tex = PixelGame.res.getTexture("idle2");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
				anim = -2;
				player.setAnimation(sprites, 1/12f, 0);
				
				
			
			} else if (anim == 3 || anim == 9 || anim == 5) {
				
				tex = PixelGame.res.getTexture("idle");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
				anim = -3;
				player.setAnimation(sprites, 1/12f, 0);
				
				
				
			}
			
			
		}
		
		
		//PLAYER JUMP
		if(MyInput.isPressed(MyInput.BUTTON1)) {
			
			if (cl.isPlayerOnGround()) {
				
				float posy = player.getPosition().y;
				
				player.getBody().applyForceToCenter(0, 200, true);
					
				
			}
			
			if(MyInput.isDown(MyInput.BUTTON1)) {
			
			if (anim == -2 || anim == 2) {
				
				Texture tex = PixelGame.res.getTexture("jumpl");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
				Animation jump = new Animation(sprites, 1);
				
				player.setAnimation(jump);
				
				System.out.println(jump.getTimesPlayed());
				
				anim = 4;
				
				
				
			} else if (anim == -3 || anim == 3) {
				
				Texture tex = PixelGame.res.getTexture("jumpr");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
				Animation jump = new Animation(sprites, 1);
				
				player.setAnimation(jump);
				
				System.out.println(jump.getTimesPlayed());
				
				anim = 5;
			
			} }
			
		}
		
		//PLAYER ATTACK
		if(MyInput.isPressed(MyInput.BUTTON4)) {
			
			if (timeAttack > 30) {
			if (anim == -2 || anim == 2 || anim == 5) {
				
				tex = PixelGame.res.getTexture("attack");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 50 , 40)[0];
				
				player.setAnimation(sprites, 1/36f, 1);
				
				anim = 8;
				
				
			} else if (anim == -3 || anim == 3 || anim == 4) {
				
				tex = PixelGame.res.getTexture("attack");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 50 , 40)[0];
				
				player.setAnimation(sprites, 1/36f, 1);
				
				anim = 9;
			}
			
			timeAttack = 1;
			
		}
		}
		
		//PLAYER LEFT
		if(MyInput.isDown(MyInput.BUTTON2)) {
			
			player.getBody().setLinearVelocity(-1.7f, player.getBody().getLinearVelocity().y);
			
			if (cl.isPlayerOnGround()) {
			if(anim != 2) {
				
				tex = PixelGame.res.getTexture("runl");
				
				TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
				if (!MyInput.isDown(MyInput.BUTTON1)) {
				anim = 2;
				player.setAnimation(sprites, 1/12f, 0);
				}
				
			}
			
			}
			
			
		//PLAYER RIGHT
		} else if (MyInput.isDown(MyInput.BUTTON3)) {

			player.getBody().setLinearVelocity(1.7f, player.getBody().getLinearVelocity().y);
			
			if (cl.isPlayerOnGround()) {
				
				if(anim != 3) {
			
					tex = PixelGame.res.getTexture("runr");
			
					TextureRegion[] sprites = TextureRegion.split(tex, 32 , 40)[0];
				
					if (!MyInput.isDown(MyInput.BUTTON1)) {
						anim = 3;
						player.setAnimation(sprites, 1/12f, 0);
						}
				
				}
			
			}
		
		} 
		
		
		
	}
		
		
	
	public void update(float dt) {
		
		handleInput(); // INPUT CHECK
		timeAttack++;
		System.out.println(anim);
		
		world.step(PixelGame.STEP, 1, 1); //WORLD UPDATE
		
		//REMOVE CRYSTALS (AFTER WORLD UPDATE CAUSE YOU CANT REMOVE BODIES WHILE WORLD IS UPDATING)
		
		Array<Body> bodies = cl.getBodiesToRemove();
		
		for (int i = 0; i < bodies.size; i++) {
			
			Body b = bodies.get(i);
			crystals.removeValue((Crystal) b.getUserData(), true);
			world.destroyBody(b);
			player.collectCrystal();
			PixelGame.res.getSound("crystal").play();
			
		}
		
		
		
		bodies.clear();
		
		player.update(dt);
		
		snake.update(dt);
		
		for (int i = 0; i < crystals.size; i++) {
			
			crystals.get(i).update(dt);	
			
		}
		
	}
	
	public void render() {
		
		//clear screen
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//cam follow
		cam.position.set(player.getPosition().x * PPM + PixelGame.vWIDTH / 6, PixelGame.vHEIGHT / 2, 0);
		
		cam.update();
		
		//draw background
		sb.begin();
		spr.draw(sb);
		sb.end();
		
		//draw tilemap
		tmr.setView(cam);
		tmr.render();
				
		//draw player
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
		
		//draw snake
		sb.setProjectionMatrix(cam.combined);
		snake.render(sb);
		
		//draw crystals
		
		for (int i = 0; i < crystals.size; i++) {
			
			crystals.get(i).render(sb);	
			
		}
		
		//draw world
		if (debug == true) {
		b2dr.render(world, b2dCam.combined);
		}
		
		//draw hud
		sb.setProjectionMatrix(hudCam.combined);
		int count = player.getNumCrystals();
		hud.render(sb, count);
		
	}
	
	public void dispose() {
		
	}
	
	private void createPlayer() {
		
		BodyDef bdef = new BodyDef();
		
		//static body = not affected by forces, cant move
		//kinematic body = not affected by forces, can move
		//dynamic body = always affected by forces, can move
		
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(160/PPM,200/PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		shape.setAsBox(8/PPM, 15/PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.bitPlayer;
		fdef.filter.maskBits = B2DVars.bitPlatform | B2DVars.bitCrystal | B2DVars.bitEnemy;
		fdef.friction = 0.5f;
		body.createFixture(fdef).setUserData("player");	
		
		//CREATE FOOT SENSOR (SENSOR == GHOST FIXTURE DETECTIN COLLISIONS)
		
		shape.setAsBox(10/PPM, 2/PPM, new Vector2(0, -18/PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.bitPlayer;
		fdef.filter.maskBits = B2DVars.bitPlatform;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot"); 
		
		//CREATE PLAYER
		
		player = new Player(body);
		
	}
	
	private void createTiles() {
		
		//LOAD TILEMAP
		tileMap = new TmxMapLoader().load("res/maps/test.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
				
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer =
				(TiledMapTileLayer) tileMap.getLayers().get("level");
		
		createLayer(layer, B2DVars.bitPlatform);
		
	}
	
	private void createLayer(TiledMapTileLayer layer, short bits) {
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		//GO THROUGH THE CELLS
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {
				
				//GET CELL
				Cell cell = layer.getCell(col, row);
				
				//CHECK IF CELL EXISTS
				if (cell == null ) continue;
				if (cell.getTile() == null) continue;
				
				//CREATE BODY AND FIXTURE FROM CELL
				bdef.type = BodyType.StaticBody;
				bdef.position.set(
						(col + 0.5f) * tileSize / PPM,   // BOX2D TAKES FROM THE CENTER SO + 0.5f
						(row + 0.5f) * tileSize / PPM
						);
				
				ChainShape cs = new ChainShape();  //POLYGONS HAVE PROBLEMS WITH PLAYER
				Vector2[] v = new Vector2[3];
				
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				
				cs.createChain(v);
				
				fdef.friction = 0.005f;
				fdef.shape = cs;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.bitPlayer | B2DVars.bitEnemy;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
		
			}	
		}
	}
	
	private void createCrystals() {
		
		crystals = new Array<Crystal>();
		
		MapLayer layer = tileMap.getLayers().get("crystals");
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		
		
		for (MapObject mo : layer.getObjects()) {
			
			bdef.type = BodyType.StaticBody;
			
			float x = mo.getProperties().get("x", Float.class) / PPM;
			float y = mo.getProperties().get("y", Float.class) / PPM;
			
			bdef.position.set(x,y);
			
			CircleShape cShape = new CircleShape();
			cShape.setRadius(6/PPM);
			
			fdef.shape = cShape;
			fdef.isSensor = true;
			fdef.filter.categoryBits = B2DVars.bitCrystal;
			fdef.filter.maskBits = B2DVars.bitPlayer;
			
			Body body = world.createBody(bdef);
			body.createFixture(fdef).setUserData("crystal");;
			Crystal c = new Crystal(body);
			crystals.add(c);
			
			body.setUserData(c);
			
			
		}
		
	}
	
	private void playMusic() {
		
		sd = PixelGame.res.getSound("theme");
		sd.loop(0.5f);
		
	}
	
	private void createBackground () {
		
		Texture back = PixelGame.res.getTexture("background");
		spr = new Sprite(back);
		
		
	}
	
	private void createEnemy() {
		
		BodyDef bdef = new BodyDef();
		
		//static body = not affected by forces, cant move
		//kinematic body = not affected by forces, can move
		//dynamic body = always affected by forces, can move
		
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		
		bdef.position.set(200/PPM,200/PPM);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		shape.setAsBox(8/PPM, 15/PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.bitEnemy;
		fdef.filter.maskBits = B2DVars.bitPlatform | B2DVars.bitPlayer;
		fdef.friction = 0.5f;
		body.createFixture(fdef).setUserData("snake");	
		
		//CREATE FOOT SENSOR (SENSOR == GHOST FIXTURE DETECTIN COLLISIONS)
		
		shape.setAsBox(10/PPM, 2/PPM, new Vector2(0, -18/PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.bitEnemy;
		fdef.filter.maskBits = B2DVars.bitPlatform | B2DVars.bitPlayer;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("snakeFoot"); 
		
		snake = new Enemy(body);
		
		
	}
	
}
