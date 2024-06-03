package group5.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {
	public SpriteBatch batch;
	public GameAssets assets;

	@Override
	public void create() {
		batch = new SpriteBatch();
		assets = new GameAssets();
		assets.load();
		assets.finishLoading();
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}
}


