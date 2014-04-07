package com.hlg.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class FPSLabel extends Actor {

	
	private LabelStyle style;
	private BitmapFont font;
	public Label label;
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		this.label.setText("FPS:" + String.valueOf(Gdx.graphics.getFramesPerSecond()));
		this.label.draw(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}

	public FPSLabel(String fntPath, String fontPngPath) {
		this.font = new BitmapFont(Gdx.files.internal(fntPath),
	               Gdx.files.internal(fontPngPath), false);
		this.style = new LabelStyle(font, font.getColor());
	 
	 
	    this.label = new Label("FPS:" + String.valueOf(Gdx.graphics.getFramesPerSecond()), style);
	    this.label.setPosition(40, Gdx.graphics.getHeight() - this.label.getHeight() - 20);
	}
	
}
