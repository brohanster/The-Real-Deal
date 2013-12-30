package com.ror.GdxGame.physicsChallenge;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.TimeUtils;
import com.ror.GdxGame.GdxGame;

public class ChallengeSimulatorScreen implements Screen{

	final GdxGame game;
	float deltaTime, eqTime, velocity, gravity, angle, angleRad;
	int prevType;
	public ChallengeSimulatorScreen(final GdxGame gam){
		game = gam;
		handleInput();
	}
	public void handleInput(){
		prevType = ChallengeDisplayScreen.challengeType;
		if(prevType == 1){
			angle = (float) ChallengeDisplayScreen.answer;
			
		}
		if(prevType == 2){
			velocity = (float) ChallengeDisplayScreen.answer;
			
		}
		if(prevType == 3){
			gravity = (float) ChallengeDisplayScreen.answer;
			
		}
		if(prevType == 4){
			velocity = (float)ChallengeDisplayScreen.exitVel;
			
		}
		if(prevType == 5){
			velocity = (float)ChallengeDisplayScreen.exitVel;
			
		}
	}
	public float x(float time){
		
		return 0;
	}
	public float y(float time){
		
		return 0;
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
