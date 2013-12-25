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
	float k, deltaTime, eqTime, angle, velocity, xVel, yVel, mult, mass, densAir, coeDrag, gravity, area, terminalVelocity;
	Texture projectile, launcher;
	Label height, distance;
	SpriteBatch batch;
	boolean started;
	public AirResistanceSimulatorScreen(final GdxGame gam){
		game = gam;
		stage = new Stage();
		table = new Table();
		retrieveInfo();
		compute();
		findMultEarth();
		densAir = 1.204f;
		gravity = 9.8f;
		k = .0431f;
		terminalVelocity = terminalVelocity();
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
	public float terminalVelocity(){
		float num = 2 * mass * gravity;
		float denom = densAir * area * coeDrag;
		float quo = num / denom;
		return (float)Math.sqrt(quo);
	}
	public void retrieveInfo(){
		angle = Float.parseFloat(VariableEnterScreen.angleField.getText());
		velocity = Float.parseFloat(VariableEnterScreen.velocityField.getText());
		if(VariableEnterScreen.objectBox.getSelection().equals("car")){
			projectile = new Texture(Gdx.files.internal("droplet.png"));
		}
		else if(VariableEnterScreen.objectBox.getSelection().equals("cannonball")){
			//projectile = cannonball image
			projectile = new Texture(Gdx.files.internal("droplet.png"));
			mass = 5.4f;
			coeDrag = .48f;
			area = (float)(Math.PI * 2.2 * 2.2);
			
			
		}
		
	}
	public void compute(){
		angle = (angle*(float)Math.PI)/180;
		xVel = (float) Math.cos(angle)*velocity;
		yVel = (float) Math.sin(angle)*velocity;
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
        		batch.draw(projectile, x(eqTime), y(eqTime));
        
        batch.end();
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();  
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
	public float x(float time){
		//ask about the use of xVel, and instantaneous velocity
		float first = (terminalVelocity * terminalVelocity)/gravity;
		float inside = ((terminalVelocity * terminalVelocity) + gravity * xVel * time)/(terminalVelocity * terminalVelocity);
		distance.setText("Distance: " + first * (float)Math.log(inside));
		return first * (float)Math.log(inside);
	}
	public float instantY(float time){
		float num = yVel - (terminalVelocity * (float)Math.tan((time * gravity)/terminalVelocity));
		float denom = terminalVelocity + (yVel * (float)Math.tan((time * gravity)/terminalVelocity));
		return terminalVelocity * (num / denom);
	}
	public float y(float time){		
		float first = (terminalVelocity * terminalVelocity)/(2 * gravity);
		float num = (yVel * yVel) + (terminalVelocity * terminalVelocity);
		float denum = (instantY(time) * instantY(time)) + (terminalVelocity * terminalVelocity);
		height.setText("Height: " + first * (float)Math.log(num/denum));
		return first * (float)Math.log(num/denum);
		
		
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
