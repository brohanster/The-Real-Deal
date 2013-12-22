package com.ror.GdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class VariableEnterScreen implements Screen{

	final GdxGame game;
	Stage stage;
	Skin s;
	Label objectLabel, velocityLabel, angleLabel, planetLabel, resistanceLabel;
	public static TextField velocityField, angleField;
	public static CheckBox resistanceBoxYes, resistanceBoxNo;
	public static SelectBox objectBox, planetBox;
	TextButton goButton;
	public VariableEnterScreen(final GdxGame gam){
		String objects[] = new String[5];//figure out number lata
		objects[0] = "cannonball";
		objects[1] = "car";
		
		String planets[] = new String[5];//again figure it out later
		planets[0] = "earth";
		planets[1] = "mars";
		planets[2] = "jupiter";
		
		game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		s = new Skin(Gdx.files.internal("uiskin.json"));
		
		goButton = new TextButton("Ready?", s);		
		
		objectLabel = new Label("What object to launch?", s);
		velocityLabel = new Label("What exit velocity?", s);
		angleLabel = new Label("What angle to launch?", s);
		planetLabel = new Label("Launch on which planet?", s);
		resistanceLabel = new Label("Enable air resistance?", s);
		Label l = new Label("", s);
		velocityField = new TextField("Enter Velocity (m/s)", s);
		angleField = new TextField("Enter Angle (degrees)", s);
		
		resistanceBoxYes = new CheckBox("Yes", s);
		resistanceBoxNo = new CheckBox("No", s);
		
		objectBox = new SelectBox(new String[]{"car", "cannonball"}, s);
		planetBox = new SelectBox(new String[]{"earth", "mars"}, s);
		
		table.add(objectLabel);
		table.add(objectBox).width(150);
		table.row();
		table.add(velocityLabel);
		table.add(velocityField).width(150);
		table.row();
		table.add(angleLabel);
		table.add(angleField).width(150);
		table.row();		
		table.add(planetLabel);
		table.add(planetBox).width(150);
		table.row();
		table.add(resistanceLabel);
		table.add(resistanceBoxYes);
		table.row();
		table.add(l);
		table.add(resistanceBoxNo);
		table.row();
		table.add(goButton).colspan(3).width(200);
		stage.addActor(table);
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
		// TODO Auto-generated method stub
		
	}

}
