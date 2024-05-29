package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Entities.Snake;
import com.mygdx.game.Entities.Apple;

/**
 * TASKS:
 * 	1. Tela de início
 * 		1.1 Design
 * 		1.2	Single PLayer
 * 		2.3 Multiplayer
 * 	2. Iniciar o jogo com a cobra parada
 * 	3. Fazer ela crescer conforme coleta as maçãs
 * 	4. Velocidade da cobra aumenta gradativamente
 * 	5. Implementar o game over
 * 		5.1 Colisão com a parede
 * 		5.2 Colisão com ela mesma
 * 		5.3 Tela de game over
 * 	6. Implementar um scoreboard
 * 	7. Sons e música
 * 	8. Implementar o Multiplayer
 * 	9. Comentários em JAVADOC (em inglês)
 * FEATURES:
 *	1.	Animação para a maçã
 *	2.	Animação ao comer a maçã
 *	3.	Animação para a boca a cobra ao se aproximar da maçã
 */



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
