package com.ror.GdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuScreen implements Screen{
	
	Table t;
	TextButton simulatorChoice;
	TextButton gameChoice;
	Label welcome;
	Skin s;
	final GdxGame game;
	Stage stage;
	public MainMenuScreen(final GdxGame gam){
		game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		t = new Table();
		t.setFillParent(true);
		s = new Skin(Gdx.files.internal("uiskin.json"));
		
		simulatorChoice = new TextButton("Run the Simulator!", s);
		simulatorChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		game.setScreen(new VariableEnterScreen(game));
        		return true;
        	}
        });
		gameChoice = new TextButton("Take the Physics Challenge!", s);
		gameChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		//game.setScreen(new DisplayScreen(game));
        		return true;
        	}
        });
		welcome = new Label("Welcome to Projectile Motion!", s);
		t.add(welcome).expandY();
		t.row();
		t.add(simulatorChoice).width(300).height(75);
		t.row();
		t.add(gameChoice).width(300).height(75);
		t.row();
		
		stage.addActor(t);
	}
	
	@Override
	public void render(float delta) {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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
