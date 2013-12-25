package com.ror.GdxGame;

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

public class AirResistanceSimulatorScreen implements Screen{

	final GdxGame game;
	Stage stage;
	Table table;
	Skin s;
	TextButton newVar, redo, main, start;
	float deltaTime, eqTime;
	Texture projectile, launcher;
	Label height, distance;
	SpriteBatch batch;
	boolean started;
	public AirResistanceSimulatorScreen(final GdxGame gam){
		game = gam;
		stage = new Stage();
		table = new Table();
		Texture.setEnforcePotImages(false);
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(stage);
		s = new Skin(Gdx.files.internal("uiskin.json"));
		height = new Label("", s);
		distance = new Label("", s);		
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
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        eqTime = (TimeUtils.nanoTime() - deltaTime)/1000000000;
        //draw the launched and launcher and background here    	   
        batch.begin();
        //if(started)
        	//if(y(eqTime) > 0)
        		//batch.draw(projectile, mult * x(eqTime), mult * y(eqTime));
        
        batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();  
	}
	
	@Override
	public void resize(int width, int height) {
		
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
		// TODO Auto-generated method stub
		
	}

}
