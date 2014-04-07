package com.hlg.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/*
 * 正交相机演员类
 * 
 * by  HuangLiGuo 2014-03-29
 */
public class OrtCameraActor extends Actor implements GestureListener {
	
	private OrthographicCamera ortCamera;
	private Image imgBackground;
	
	private float imgBackWidth, imgBackHeight;
	private float screenWidth, screenHeight;
	private float leftLimit, bottomLimit, topLimit, rightLimit;
	
	public float zoom;
	public float minZoom = 0.5f, maxZoom = 1.5f;
	private float preZoomDistance = 0.0f;
	
	public OrtCameraActor(String imgBackPath) {
		this.ortCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.imgBackground = new Image(new Texture(Gdx.files.internal(imgBackPath)));
		
		this.imgBackWidth = this.imgBackground.getWidth();
		this.imgBackHeight = this.imgBackground.getHeight();
		
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();
		
		this.CalcLimit();
		
		this.ortCamera.position.set(this.screenWidth / 2, this.screenHeight / 2, 0);
		this.ortCamera.update();
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		batch.setProjectionMatrix(this.ortCamera.combined);
		this.imgBackground.draw(batch, 1);
		super.draw(batch, parentAlpha);
	}
	
	public void animLookAt(float x, float y) {
		
	}
	
	public void animLookAt(float x, float y, float x2, float y2) {
		
	}

	private void CalcLimit() {
		this.leftLimit = (this.screenWidth / 2) * this.ortCamera.zoom;
		this.bottomLimit = (this.screenHeight / 2) * this.ortCamera.zoom;
		this.rightLimit = (this.imgBackWidth - (this.screenWidth / 2) * this.ortCamera.zoom);
		this.topLimit = (this.imgBackHeight - (this.screenHeight / 2) * this.ortCamera.zoom);
	}
	private void CheckLimit(){
		if(this.ortCamera.position.x < this.leftLimit) {
			this.ortCamera.position.x = this.leftLimit;
		}
		if(this.ortCamera.position.y < this.bottomLimit) {
			this.ortCamera.position.y = this.bottomLimit;
		}
		if(this.ortCamera.position.x > this.rightLimit){
			this.ortCamera.position.x = this.rightLimit;
		}
		if(this.ortCamera.position.y > this.topLimit){
			this.ortCamera.position.y = this.topLimit;
		}
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		this.ortCamera.translate(-deltaX, deltaY);
		
		this.CheckLimit();
		this.ortCamera.update();
		return false;
	}
	
	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		if(this.preZoomDistance != distance) {
			float dis = distance - this.preZoomDistance;
			if(Math.abs(dis) > 2) {
				if(dis > 0) {
					this.ortCamera.zoom -= 0.01;
					if(this.ortCamera.zoom <= this.minZoom) {
						this.ortCamera.zoom = this.minZoom;
					}
				} else if(dis < 0) {
					this.ortCamera.zoom += 0.01;
					if(this.ortCamera.zoom >= this.maxZoom) {
						this.ortCamera.zoom = this.maxZoom;
					}
				}
				this.CalcLimit();
				this.CheckLimit();
				this.preZoomDistance = distance;
				this.ortCamera.update();
			}
		} else {
			
		}
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}
}
