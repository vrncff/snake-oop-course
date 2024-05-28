package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entities.Snake;
import com.mygdx.game.Entities.Apple;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imgBackGroung, imgSnake, imgApple;
	Snake snake;
	Apple apple;

	@Override
	public void create() {
		batch = new SpriteBatch();
		imgBackGroung = new Texture("bg.jpg");
		imgSnake = new Texture("head_right.png");
		imgApple = new Texture("apple.png");
		snake = new Snake(imgSnake);
		apple = new Apple(imgApple);
	}

	@Override
	public void render() {
		snake.move();
		snake.checkCollision(apple);

		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(imgBackGroung, 0, 0);
		snake.draw(batch);
		apple.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		imgBackGroung.dispose();
		snake.dispose();
		apple.dispose();
	}
}
