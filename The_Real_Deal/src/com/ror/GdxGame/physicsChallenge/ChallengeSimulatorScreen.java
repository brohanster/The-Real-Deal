package com.ror.GdxGame.physicsChallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;
import com.ror.GdxGame.GdxGame;

public class ChallengeSimulatorScreen implements Screen{

	final GdxGame game;
	float deltaTime, eqTime, velocity, gravity, angle, angleRad, targetX, targetY, xVel, yVel, mult;
	int prevType;
	boolean started = false;
	Texture projectile, launcher, target;
	Table table;
	Stage stage;
	TextButton start;
	Label height, distance;
	Skin s;
	SpriteBatch batch;	
	public ChallengeSimulatorScreen(final GdxGame gam){
		game = gam;
		targetX = 0;
		targetY = 0;
		handleInput();		
		Texture.setEnforcePotImages(false);
		projectile = new Texture(Gdx.files.internal("droplet.png"));
		target = new Texture(Gdx.files.internal("bucket.png"));
		batch = new SpriteBatch();
		s = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		height = new Label("", s);//not sure about these labels, once the screen is done ill decide on em
		distance = new Label("", s);
		start = new TextButton("Start!",s);
		start.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		deltaTime = TimeUtils.nanoTime();
        		started = true;
        		return true;
        	}
        });
		table.top();
		table.add(start).width(100);
		table.add(distance).width(100);
		table.add(height).width(100);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}
	public void handleInput(){
		prevType = ChallengeDisplayScreen.challengeType;
		if(prevType == 1){
			angle = (float) ChallengeDisplayScreen.answer;
			gravity = 10;
			velocity = ChallengeDisplayScreen.exitVel;
			angleRad = (float)(angle * Math.PI/180);
			targetX = (float) (ChallengeDisplayScreen.xVel * ChallengeDisplayScreen.time*2);
			
		}
		if(prevType == 2){
			velocity = (float) ChallengeDisplayScreen.answer;
			float inputVel = (float)ChallengeDisplayScreen.inputAnswer;
			angle = ChallengeDisplayScreen.angle;
			gravity = 10;
			angleRad = (float)(angle * Math.PI/180);
			targetX = (float) (ChallengeDisplayScreen.xVel * ChallengeDisplayScreen.time * 2);			
		}
		if(prevType == 3){
			gravity = (float) ChallengeDisplayScreen.answer;
			angle = ChallengeDisplayScreen.angle;
			angleRad = (float)(angle * Math.PI/180);
			velocity = ChallengeDisplayScreen.exitVel;
			targetX = (float) (ChallengeDisplayScreen.xVel * ChallengeDisplayScreen.time * 2);
		}
		if(prevType == 4){
			velocity = (float)ChallengeDisplayScreen.exitVel;
			gravity = 10;
			angle = ChallengeDisplayScreen.angle;
			angleRad = (float)(angle * Math.PI/180);
			targetX = (float) ChallengeDisplayScreen.answer;
			//need to add target y and x in thes
		}
		if(prevType == 5){
			velocity = (float)ChallengeDisplayScreen.exitVel;
			gravity = 10;
			angle = ChallengeDisplayScreen.angle;
			angleRad = (float)(angle * Math.PI/180);
			targetY = (float)ChallengeDisplayScreen.answer;
		}
		xVel = velocity * (float)Math.cos(angleRad);
		yVel = velocity * (float)Math.sin(angleRad);
		System.out.println(angle + "," + velocity + "," + targetX);
		findMult();
		targetX*=mult;
		targetY*=mult;
	}
	public float x(float time){
		float dist = time*xVel;
		if(y(time) > 0)
			distance.setText("Distance: " + (int)dist);
		return dist;
	}
	public float y(float time){
		float hei = ((yVel * time) - (0.5f * gravity * (time * time)));
		if(hei > 0)
			height.setText("Height: " + (int)hei);		
		return hei;
	}
	public void findMult(){
		if(velocity <= 10)
			mult = 60f;
		else if(velocity > 10 && velocity <= 20)
			mult = 20f;
		else if(velocity > 20 && velocity <= 40)
			mult = 5f;
		else if(velocity > 40 && velocity <= 80)
			mult = 1.1f;
		else
			mult = .9f;			
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		eqTime = (TimeUtils.nanoTime() - deltaTime)/1000000000;
		batch.begin();
		if(started)
			if(y(eqTime)>0)
				batch.draw(projectile, mult * x(eqTime), mult * y(eqTime));
		batch.draw(target, targetX, targetY);
		batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
