package group5.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import group5.snake.screens.GameScreen;

/**
 * The SnakeGame class extends the Game class from the libGDX library.
 * It represents the main class of the game, where the game assets are loaded and the game screen is set.
 */
public class SnakeGame extends Game {
	public SpriteBatch batch;
	public GameAssets assets;

	/**
	 * Called when the application is created.
	 * Initializes the SpriteBatch, loads the game assets, and sets the screen to the game screen.
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		assets = new GameAssets();
		assets.load();
		assets.finishLoading();
		this.setScreen(new GameScreen(this));
	}

	/**
	 * Called by the game loop from the application every time rendering should be performed.
	 * Renders the game.
	 */
	@Override
	public void render() {
		super.render();
	}

	/**
	 * Called when the application is destroyed.
	 * Disposes of all assets in the game.
	 */
	@Override
	public void dispose() {
		batch.dispose();
		assets.dispose();
	}
}


