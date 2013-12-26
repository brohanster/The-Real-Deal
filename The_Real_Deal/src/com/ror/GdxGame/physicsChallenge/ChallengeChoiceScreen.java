package com.ror.GdxGame.physicsChallenge;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.ror.GdxGame.GdxGame;

public class ChallengeChoiceScreen implements Screen{

	final GdxGame game;
	Label challengeChoice;
	Skin s;
	Stage stage;
	Table table;
	TextButton backButton, angleChoice, velocityChoice, gravityChoice, distanceChoice;//We can add more, but ill leave it at this for now
	
	public ChallengeChoiceScreen(final GdxGame gam){
		game = gam;
	}
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
		// TODO Auto-generated method stub
		
	}

}
