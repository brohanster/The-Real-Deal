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
import com.ror.GdxGame.GdxGame;
import com.ror.GdxGame.MainMenuScreen;

public class ChallengeChoiceScreen implements Screen{

	final GdxGame game;
	Label challengeChoice;
	Skin s;
	Stage stage;
	Table table;
	TextButton backButton, angleChoice, velocityChoice, gravityChoice, distanceChoice, heightChoice, randomChoice;//We can add more, but ill leave it at this for now
	SpriteBatch batch;
	Texture background;
	public static int challengeType;
	public ChallengeChoiceScreen(final GdxGame gam){
		game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		s = new Skin(Gdx.files.internal("uiskin.json"));
		challengeChoice = new Label("Which challenge would you like to take?", s);
		batch = new SpriteBatch();
		Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("wallpaper.jpg"));
		backButton = new TextButton("Back", s);
		backButton.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		game.setScreen(new MainMenuScreen(game));
        		return true;
        	}
        });
		angleChoice = new TextButton("Angle Challenge", s);
		angleChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 1;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		velocityChoice = new TextButton("Velocity Challenge", s);
		velocityChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 2;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		gravityChoice = new TextButton("Gravity Challenge", s);
		gravityChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 3;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		distanceChoice = new TextButton("Distance Challenge", s);
		distanceChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 4;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		heightChoice = new TextButton("Height Challenge", s);
		heightChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 5;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		randomChoice = new TextButton("Random!", s);
		randomChoice.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		challengeType = 6;
        		game.setScreen(new ChallengeDisplayScreen(game));
        		return true;
        	}
        });
		table.setFillParent(true);
		table.setSkin(s);
		table.center();
		table.add(challengeChoice).colspan(2);
		table.row();
		table.add(" ").height(60);
		table.row();
		table.add(angleChoice).height(60).width(250);
		//table.row();
		table.add(velocityChoice).height(60).width(250);
		table.row();
		table.add(gravityChoice).height(60).width(250);
		//table.row();
		table.add(distanceChoice).height(60).width(250);
		table.row();
		table.add(heightChoice).height(60).width(250);
		//table.row();
		table.add(randomChoice).height(60).width(250);
		stage.addActor(table);
	}
	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(background, 0,0);
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
