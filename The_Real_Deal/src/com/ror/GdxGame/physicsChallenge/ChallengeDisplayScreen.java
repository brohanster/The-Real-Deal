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
	public static double answer;//use a +- of like 5 to accept a variety of answers
	Label title, info1, info2, info3, info4, inputLabel;
	String titleL, info1L, info2L, info3L, info4L;
	TextField input;
	public static int angle, gravity, distance, exitVel;
	double time = 0;
	TextButton testAnswer;
	Stage stage;
	Table table;
	Skin s;
	Random randGen;
	SpriteBatch batch;
	Texture background;
	public ChallengeDisplayScreen(final GdxGame gam){
		challengeType = ChallengeChoiceScreen.challengeType;
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
        		//could do something like show the answer simulated whether right or not, then give another try if incorrect
        		if(checkAns(answer, input)){
        			
        		}
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
		table.add(testAnswer).width(150).height(35);
		stage.addActor(table);
		
	}
	public boolean checkAns(double ans, TextField t){
		
		if(!t.getText().matches("-?\\d+(\\.\\d+)?"))
			//how to say thats not a number, or just say thats not correct
			return false;
		
		if(Integer.parseInt(t.getText()) > ans - 2 && Integer.parseInt(t.getText()) < ans + 2)
			return true;		
		else		
			return false;
		
	}
	public void handleInput(){
		if(challengeType == 6){
			challengeType = (randGen.nextInt(5) + 1);
		}
		if(challengeType == 1){
			spawnAngleProblem();
		}
		if(challengeType == 2){
			spawnVelocityProblem();
		}
		if(challengeType == 3){
			spawnGravityProblem();
		}
		if(challengeType == 4){
			spawnDistanceProblem();
		}
		if(challengeType == 5){
			spawnHeightProblem();
		}
	}
	public void spawnAngleProblem(){
		//give exit velocity, 
		//distance, max height, or 
		answer = randGen.nextInt(80) + 10;
		exitVel = randGen.nextInt(65) + 10;
		gravity = 10;//ease of calculation
		int variant = randGen.nextInt(2) + 1;
		titleL = "Find the exit velocity: ";
		info1L = "The exit velocity is " + exitVel + " m/s";
		info2L = "Assume gravity is 10 m/s";
		double angleRad = (answer * Math.PI/180);
		double xVel = Math.cos(angleRad) * exitVel;
		double yVel = Math.sin(angleRad) * exitVel;
		time = yVel/gravity;
		info4L = "The total time of flight is " + new DecimalFormat("#.##").format(time) + " seconds";
		if(variant == 1){//given distance of flight			
			distance = (int)(time * xVel);
			info3L = "The particle distance is " + distance + " meters";
		}
		if(variant == 2){//given max height
			int maxHeight = (int)(yVel * (time/2) - 4.9 * ((time * time)/4));
			info3L = "The particle's max height is " + maxHeight + " meters";
		}
	}
	public void spawnVelocityProblem(){
		//finding exit velocity
		//need to give max height?, distance, angle i think
		//i think angle is almost mandatory, and then giving time of flight or anything you can get it
		angle = randGen.nextInt(80) + 10;
		answer = randGen.nextInt(65) + 10;
		gravity = 10;//ease of calculation
		int variant = randGen.nextInt(2) + 1;
		titleL = "Find the exit velocity: ";
		info1L = "The angle is " + angle + " degrees";
		info2L = "Assume gravity is 10 m/s";
		double angleRad = (angle * Math.PI/180);
		double xVel = Math.cos(angleRad) * answer;
		double yVel = Math.sin(angleRad) * answer;
		time = yVel/gravity;
		info4L = "The total time of flight is " + new DecimalFormat("#.##").format(time) + " seconds";
		if(variant == 1){//given distance of flight			
			distance = (int)(time * xVel);
			info3L = "The particle distance is " + distance + " meters";
		}
		if(variant == 2){//given max height
			int maxHeight = (int)(yVel * (time/2) - 4.9 * ((time * time)/4));
			info3L = "The particle's max height is " + maxHeight + " meters";
		}
		
	}
	public void spawnGravityProblem(){
		double temp = randGen.nextDouble()*10;
		answer = Double.valueOf(new DecimalFormat("#.##").format(temp));
		//so easiest way to get a good problem would be to give the y value at a given time t, and give velocity and angle
		exitVel = randGen.nextInt(40) + 10;
		angle = randGen.nextInt(80) + 10;
		double angleRad = angle * Math.PI /180;
		double yVel = exitVel * Math.sin(angleRad);
		double time = Double.valueOf(new DecimalFormat("#.##").format(randGen.nextDouble() * 3));
		double tempY = (yVel * time) + (.5 * answer * (time * time));
		double y = Double.valueOf(new DecimalFormat("#.##").format(tempY));
		titleL = "Find the acceleration of gravity: ";
		info1L = "The exit velocity is " + exitVel + " m/s";
		info2L = "The angle of launch is " + angle + " degrees";
		info3L = "The height at time " + time + " seconds is " + y + " meters";
	}
	public void spawnDistanceProblem(){
		angle = randGen.nextInt(80) + 10;
		exitVel = randGen.nextInt(60) + 10;
		titleL = "Find the distance the particle will go:";
		info1L = "The angle of launch is " + angle + " degrees";
		info2L = "The exit velocity is " + exitVel + " m/s";
		info3L = "Assume gravity is 10 m/s";
		double angleRad = angle * Math.PI / 180;
		double xVel = exitVel * Math.cos(angleRad);
		double yVel = Math.sin(angleRad) * answer;
		time = yVel/10;
		answer = Double.valueOf(new DecimalFormat("#.##").format(xVel * time));
		
	}
	public void spawnHeightProblem(){
		angle = randGen.nextInt(80) + 10;
		exitVel = randGen.nextInt(60) + 10;
		titleL = "Find the maximum height of the particle:";
		info1L = "The angle of launch is " + angle + " degrees";
		info2L = "The exit velocity is " + exitVel + " m/s";
		info3L = "Assume gravity is 10 m/s";
		double angleRad = angle * Math.PI / 180;
		double yVel = Math.sin(angleRad) * answer;
		time = yVel/10;
		answer = Double.valueOf(new DecimalFormat("#.##").format((yVel * (time/2) - (4.9 * ((time * time)/4)))));
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
