package com.ror.GdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.TimeUtils;

public class VariableEnterScreen implements Screen{

	final GdxGame game;
	Stage stage;
	Skin s;
	Table table;
	Label objectLabel, velocityLabel, angleLabel, planetLabel, resistanceLabel;
	public static TextField velocityField, angleField;
	public static CheckBox resistanceBoxYes, resistanceBoxNo;
	public static SelectBox objectBox, planetBox;
	Dialog velocityNumWrong, velocityNotNum, angleNotNum, angleNumWrong, resistanceIssue;
	TextButton goButton, backButton;
	public VariableEnterScreen(final GdxGame gam){		
		game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		s = new Skin(Gdx.files.internal("uiskin.json"));
		
		goButton = new TextButton("Ready?", s);
		TextButton okAW = new TextButton("ok", s);
		TextButton okAN = new TextButton("ok", s);
		TextButton okVW = new TextButton("ok", s);
		TextButton okVN = new TextButton("ok", s);
		TextButton okRI = new TextButton("ok", s);
		backButton = new TextButton("Back", s);
		backButton.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		game.setScreen(new MainMenuScreen(game));
        		return true;
        	}
        });
		okAW.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){        		
        		angleNumWrong.hide();
        		return true;
        	}
        });
		okAN.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){        		
        		angleNotNum.hide();
        		return true;
        	}
        });
		okVW.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){        		
        		velocityNumWrong.hide();
        		return true;
        	}
        });
		okVN.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){        		
        		velocityNotNum.hide();
        		return true;
        	}
        });
		okRI.addListener(new InputListener(){
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){        		
        		resistanceIssue.hide();
        		return true;
        	}
        });
		goButton.addListener(new InputListener(){			
        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
        		if(!isNumeric(velocityField.getText())){
        			velocityNotNum.show(stage);
        			return true;
        		}        		
        		if(Integer.parseInt(velocityField.getText()) > 100 || Integer.parseInt(velocityField.getText()) < 0){
        			velocityNumWrong.show(stage);
        			return true;
        		}
        		if(!isNumeric(angleField.getText())){
        			angleNotNum.show(stage);        			
        			return true;
        		}
        		if(Integer.parseInt(angleField.getText()) > 90 || Integer.parseInt(angleField.getText()) < 0){
        			angleNumWrong.show(stage);
        			return true;
        		}
        		if(resistanceBoxYes.isChecked() && resistanceBoxNo.isChecked()){
        			resistanceIssue.show(stage);
        			return true;
        		}
        		if(!resistanceBoxYes.isChecked() && !resistanceBoxNo.isChecked()){
        			resistanceIssue.show(stage);
        			return true;
        		}
        		if(resistanceBoxYes.isChecked()){
        			game.setScreen(new AirResistanceSimulatorScreen(game));
        			return true;
        		}
        		if(resistanceBoxNo.isChecked()){
        			game.setScreen(new SimulationScreen(game));
        			return true;
        		}
        		return true;
        	}
        });
		
		velocityNumWrong = new Dialog("Velocity must be between 1-100", s);
		velocityNotNum = new Dialog("Please enter a number for velocity", s);			
		angleNumWrong = new Dialog("Angle must be between 1-89", s);
		angleNotNum = new Dialog("Please enter a number for angle", s);
		resistanceIssue = new Dialog("Please check 1 resistance option", s);
		
		velocityNotNum.add(okVN).width(300);	
		velocityNumWrong.add(okVW).width(300);
		angleNumWrong.add(okAW).width(300);
		angleNotNum.add(okAN).width(300);
		resistanceIssue.add(okRI).width(300);
		
		objectLabel = new Label("What object to launch?", s);
		velocityLabel = new Label("What exit velocity (m/s)?", s);
		angleLabel = new Label("What angle to launch?", s);
		planetLabel = new Label("Launch on which planet?", s);
		resistanceLabel = new Label("Enable air resistance?", s);
		Label l = new Label("", s);
		velocityField = new TextField("What exit velocity (m/s)?", s);
		angleField = new TextField("Enter Angle (degrees)", s);
		
		resistanceBoxYes = new CheckBox("Yes", s);
		resistanceBoxNo = new CheckBox("No", s);
		
		objectBox = new SelectBox(new String[]{"car", "cannonball"}, s);
		planetBox = new SelectBox(new String[]{"earth", "mars"}, s);
		
		table.add(objectLabel);
		table.add(objectBox).width(200);
		table.row();
		table.add(velocityLabel);
		table.add(velocityField).width(200);
		table.row();
		table.add(angleLabel);
		table.add(angleField).width(200);
		table.row();		
		table.add(planetLabel);
		table.add(planetBox).width(200);
		table.row();
		table.add(resistanceLabel);
		table.add(resistanceBoxYes);
		table.row();
		table.add(l);
		table.add(resistanceBoxNo);
		table.row();
		table.add(goButton).colspan(3).width(200);
		table.add(backButton).colspan(3).width(200);
		stage.addActor(table);
	}
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	@Override
	public void render(float delta) {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//draw the background here
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
