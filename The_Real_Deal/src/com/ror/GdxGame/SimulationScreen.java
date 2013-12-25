package com.ror.GdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;

public class SimulationScreen implements Screen{

	final GdxGame game;
	Texture projectile, launcher;
	SpriteBatch batch;
	float angle, velocity, xVel, yVel, deltaTime, gravity, mult, eqTime;
	boolean started;
	TextButton newVar, redo, main, start;
	Label height, distance;
	Skin s;
	Stage stage;
	Table table;
	public SimulationScreen(final GdxGame gam){
		game = gam;
		retrieveInfo();		
		compute();
		started = false;
		Texture.setEnforcePotImages(false);
		stage = new Stage();
		batch = new SpriteBatch();
		s = new Skin(Gdx.files.internal("uiskin.json"));
		Gdx.input.setInputProcessor(stage);
		height = new Label("", s);
		distance = new Label("", s);
		findMultEarth();
		table = new Table();
		table.setSkin(s);
		table.setFillParent(true);
		start = new TextButton("Start!", s);
		start.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		started = true;       
        		deltaTime = TimeUtils.nanoTime();
        		return true;
        	}
        });
		newVar = new TextButton("New Variables", s);
		newVar.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		game.setScreen(new VariableEnterScreen(game));        		
        		return true;
        	}
        });
		redo = new TextButton("Rerun Simulation", s);
		redo.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		deltaTime = TimeUtils.nanoTime();
        		return true;
        	}
        });
		main = new TextButton("Main Menu", s);
		main.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		game.setScreen(new MainMenuScreen(game));
        		return true;
        	}
        });
		table.top();
		table.add(start).width(150);
		table.add("Options: ").width(100);
		table.add(newVar);
		table.add(redo);
		table.add(main);
		table.add(height).width(100);
		table.add(distance).width(100);
		stage.addActor(table);
	}
	public void retrieveInfo(){
		angle = Float.parseFloat(VariableEnterScreen.angleField.getText());
		velocity = Float.parseFloat(VariableEnterScreen.velocityField.getText());
		if(VariableEnterScreen.objectBox.getSelection().equals("car")){
			projectile = new Texture(Gdx.files.internal("droplet.png"));
		}
		else if(VariableEnterScreen.objectBox.getSelection().equals("cannonball")){
			projectile = new Texture(Gdx.files.internal("droplet.png"));
		}
		else if(VariableEnterScreen.objectBox.getSelection().equals("human")){
			projectile = new Texture(Gdx.files.internal("droplet.png"));
		}
		if(VariableEnterScreen.planetBox.getSelection().equals("earth")){
			gravity = 9.8f;
			findMultEarth();
		}
		else if(VariableEnterScreen.planetBox.getSelection().equals("mars")){
			gravity = 3.711f;
			mult = 1f;
		}
		else if(VariableEnterScreen.planetBox.getSelection().equals("moon")){
			gravity = 1.622f;
			mult = 1f;
		}
		
	}
	public void compute(){
		angle = (angle*(float)Math.PI)/180;
		xVel = (float) Math.cos(angle)*velocity;
		yVel = (float) Math.sin(angle)*velocity;
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
	public void findMultEarth(){
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
        //draw the launched and launcher and background here    	   
        batch.begin();
        if(started)
        	if(y(eqTime) > 0)
        		batch.draw(projectile, mult * x(eqTime), mult * y(eqTime));
        
        batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();  
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
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
