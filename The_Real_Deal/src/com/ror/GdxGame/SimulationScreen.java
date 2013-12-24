package com.ror.GdxGame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimulationScreen implements Screen{

	final GdxGame game;
	Texture projectile, launcher;
	SpriteBatch batch;
	OrthographicCamera cam;
	float angle, velocity, xVel, yVel, deltaTime, gravity;
	public SimulationScreen(final GdxGame gam){
		game = gam;
		retrieveInfo();		
		compute();
		batch = new SpriteBatch();
	}
	public void retrieveInfo(){
		angle = Float.parseFloat(VariableEnterScreen.angleField.getText());
		velocity = Float.parseFloat(VariableEnterScreen.velocityField.getText());
		if(VariableEnterScreen.objectBox.getSelection().equals("car")){
			//projectile = car image
		}
		else if(VariableEnterScreen.objectBox.getSelection().equals("cannonball")){
			//projectile = cannonball image
		}
		if(VariableEnterScreen.planetBox.getSelection().equals("earth")){
			gravity = 9.8f;
		}
		else if(VariableEnterScreen.planetBox.getSelection().equals("mars")){
			//gravity = mars gravity
		}
		
	}
	public void compute(){
		
	}
	/*
	public float x(float time){
		
	}
	public float y(float time){
		
	}
	*/
	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}

}
