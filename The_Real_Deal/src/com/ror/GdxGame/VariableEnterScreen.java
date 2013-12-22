package com.ror.GdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class VariableEnterScreen implements Screen{

	final GdxGame game;
	Stage stage;
	Skin s;
	Label objectLabel, velocityLabel, angleLabel, planetLabel, resistanceLabel;
	public static TextField velocityField, angleField;
	public static CheckBox resistanceBox;
	public static SelectBox objectBox, planetBox;
	public VariableEnterScreen(final GdxGame gam){
		game = gam;
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		s = new Skin(Gdx.files.internal("uiskin.json"));
		
		Label l = new Label("herro", s);
		
		objectLabel = new Label("What object to launch?", s);
		velocityLabel = new Label("What exit velocity?", s);
		angleLabel = new Label("What angle to launch?", s);
		planetLabel = new Label("Launch on which planet?", s);
		resistanceLabel = new Label("Enable air resistance?", s);
		
		velocityField = new TextField("Enter Velocity (m/s)", s);
		angleField = new TextField("Enter Angle (degrees)", s);
		
		
		
		table.add(l);
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
