package com.ror.GdxGame.physicsChallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
	float deltaTime, eqTime, velocity, gravity, angle, angleRad, targetX, targetY, xVel, yVel;
	int prevType;
	Texture projectile, launcher, target;
	Table table;
	Stage stage;
	TextButton start;
	Label height, distance;
	Skin s;
	SpriteBatch batch;	
	public ChallengeSimulatorScreen(final GdxGame gam){
		game = gam;
		handleInput();
		Texture.setEnforcePotImages(false);
		batch = new SpriteBatch();
		s = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage();
		table = new Table();
		height = new Label("", s);//not sure about these labels, once the screen is done ill decide on em
		distance = new Label("", s);
		start = new TextButton("Start!",s);
		start.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		
        		return true;
        	}
        });
		Gdx.input.setInputProcessor(stage);
	}
	public void handleInput(){
		prevType = ChallengeDisplayScreen.challengeType;
		if(prevType == 1){
			angle = (float) ChallengeDisplayScreen.answer;
			gravity = 10;
			velocity = ChallengeDisplayScreen.exitVel;
			angleRad = (float)(angle * Math.PI/180);
			
		}
		if(prevType == 2){
			velocity = (float) ChallengeDisplayScreen.answer;
			angle = ChallengeDisplayScreen.angle;
			gravity = 10;
			angleRad = (float)(angle * Math.PI/180);
		}
		if(prevType == 3){
			gravity = (float) ChallengeDisplayScreen.answer;
			angle = ChallengeDisplayScreen.angle;
			angleRad = (float)(angle * Math.PI/180);
			velocity = ChallengeDisplayScreen.exitVel;
		}
		if(prevType == 4){
			velocity = (float)ChallengeDisplayScreen.exitVel;
			gravity = 10;
			angle = ChallengeDisplayScreen.angle;
			angleRad = (float)(angle * Math.PI/180);
			targetX = (float) ChallengeDisplayScreen.answer;
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
	@Override
	public void render(float delta) {
		eqTime = (TimeUtils.nanoTime() - deltaTime)/1000000000;
		
		
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
