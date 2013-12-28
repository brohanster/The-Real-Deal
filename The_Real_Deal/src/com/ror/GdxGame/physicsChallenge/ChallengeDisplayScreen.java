package com.ror.GdxGame.physicsChallenge;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.ror.GdxGame.GdxGame;

public class ChallengeDisplayScreen implements Screen{

	//i want a different background for this screen/branch
	final GdxGame game;
	static int challengeType;//1 = angle, 2 = velocity, 3 = gravity, 4= distance, 5= random
	int answer;//use a +- of like 5 to accept a variety of answers
	Label title, info1, info2, info3, info4, inputLabel;
	String titleL, info1L, info2L, info3L, info4L;
	TextField input;
	int angle, gravity, distance;
	double time = 0;
	TextButton testAnswer;
	Stage stage;
	Table table;
	Skin s;
	Random randGen;
	SpriteBatch batch;
	Texture background;
	public ChallengeDisplayScreen(final GdxGame gam){
		game = gam;
		randGen = new Random();
		handleInput();
		batch = new SpriteBatch();
		Texture.setEnforcePotImages(false);
		background = new Texture(Gdx.files.internal("wallpaper.jpg"));
		stage = new Stage();
		table = new Table();
		s = new Skin(Gdx.files.internal("uiskin.json"));
		Gdx.input.setInputProcessor(stage);
		input = new TextField("Answer", s);
		testAnswer = new TextButton("Check your answer", s);
		testAnswer.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		
        		return true;
        	}
        });
		title = new Label(titleL, s);
		info1 = new Label(info1L, s);
		info2 = new Label(info2L, s);
		info3 = new Label(info3L, s);
		info4 = new Label(info4L, s);
		table.setFillParent(true);
		table.center();
		table.add(title);
		table.row();
		table.add(info1);
		table.row();
		table.add(info2);
		table.row();
		table.add(info3);
		table.row();
		table.add(info4);
		table.row();
		table.add(input);
		table.row();
		table.add(testAnswer);
		stage.addActor(table);
		
	}
	public void handleInput(){
		if(ChallengeChoiceScreen.challengeType == 5){
			challengeType = randGen.nextInt(3) + 1;
		}
		if(ChallengeChoiceScreen.challengeType == 1||challengeType == 1){
			spawnAngleProblem();
		}
		if(ChallengeChoiceScreen.challengeType == 2||challengeType == 2){
			spawnVelocityProblem();
		}
		if(ChallengeChoiceScreen.challengeType == 3||challengeType == 3){
			spawnGravityProblem();
		}
		if(ChallengeChoiceScreen.challengeType == 4||challengeType == 4){
			spawnDistanceProblem();
		}
	}
	public void spawnAngleProblem(){
		
	}
	public void spawnVelocityProblem(){
		//finding exit velocity
		//need to give max height?, distance, angle i think
		//i think angle is almost mandatory, and then giving time of flight or anything you can get it
		angle = randGen.nextInt(80) + 10;
		answer = randGen.nextInt(65) + 10;
		gravity = 10;//ease of calculation
		int variant = 1;//randGen.nextInt(1) + 1;
		titleL = "Find the exit velocity: ";
		info1L = "The angle is " + angle + " degrees";
		info2L = "Assume gravity is 10 m/s";
		if(variant == 1){//give distance of flight
			double angleRad = (angle * Math.PI/180);
			double xVel = Math.cos(angleRad) * answer;
			double yVel = Math.sin(angleRad) * answer;
			time = yVel/gravity;
			distance = (int)(time * xVel);
			info3L = "The particle distance is " + distance + " meters";
			info4L = "The time of flight is " + new DecimalFormat("#.##").format(time) + " seconds";
		}
		if(variant == 2){//give max height
			
		}
		
	}
	public void spawnGravityProblem(){
		
	}
	public void spawnDistanceProblem(){
		
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
