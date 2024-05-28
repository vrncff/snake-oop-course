package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SnakeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgBackGroung, imgSnake;
	private Sprite snake;
	private float posX, posY;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		imgBackGroung = new Texture("bg.jpg");
		imgSnake = new Texture("badlogic.jpg");
		snake = new Sprite(imgSnake);
		posX = 0;
		posY = 0;
	}

	@Override
	public void render () {
		this.moveSnake();

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(imgBackGroung, 0, 0);
		batch.draw(snake, posX, posY);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgBackGroung.dispose();
		imgSnake.dispose();
	}

	private void moveSnake() {
		if (Gdx.input.isKeyPressed(Input.Keys.D))
			posX += 10;
		if (Gdx.input.isKeyPressed(Input.Keys.A))
			posX -= 10;
		if (Gdx.input.isKeyPressed(Input.Keys.W))
			posY += 10;
		if (Gdx.input.isKeyPressed(Input.Keys.S))
			posY -= 10;
	}
}
