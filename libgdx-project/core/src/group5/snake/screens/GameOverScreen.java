package group5.snake.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import group5.snake.SnakeGame;

/**
 * This class represents the game over screen for the Snake game.
 * It handles the rendering of the game over screen and user input to restart the game.
 */
public class GameOverScreen implements Screen {
    final SnakeGame game;
    OrthographicCamera camera;
    Texture backgroundTexture;
    Sound selected;
    BitmapFont font;
    int score1, score2;
    boolean isMultiplayer;
    String winner;

    /**
     * Constructor for the GameOverScreen class.
     * Initializes the game, camera, and background texture.
     * @param game the current game instance
     * @param score1 the score of player 1
     * @param score2 the score of player 2 (or 0 if single player)
     * @param isMultiplayer true if it's a multiplayer game
     */
    public GameOverScreen(final SnakeGame game, int score1, int score2, boolean isMultiplayer) {
        this.game = game;
        this.score1 = score1;
        this.score2 = score2;
        this.isMultiplayer = isMultiplayer;

        // Determina o ganhador
        if (isMultiplayer) {
            if (score1 > score2) {
                winner = "Player 1 Wins!";
            } else if (score2 > score1) {
                winner = "Player 2 Wins!";
            } else {
                winner = "It's a Tie!";
            }
        }
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        backgroundTexture = game.assets.getTexture("gameOverScreen.png");
        selected = game.assets.getSound("selected.wav");
        font = new BitmapFont();
    }

    /**
     * Renders the game over screen and checks for user input to restart the game.
     * @param delta time since last frame
     */
    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, 800, 480);
        font.setColor(Color.BLACK);

        if (isMultiplayer) {
            font.draw(game.batch, "Player 1 Score: " + score1, 330, 300);
            font.draw(game.batch, "Player 2 Score: " + score2, 330, 260);
            font.draw(game.batch, winner, 330, 200);
        } else {
            font.draw(game.batch, "Final Score: " + score1, 350, 220);
        }
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            selected.play();
            game.setScreen(new HomeScreen(game));
            dispose();
        }
    }

    /**
     * Called when this screen becomes the current screen of the game.
     */
    @Override
    public void show() {}

    /**
     * Called when the screen is resized.
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(int width, int height) {}

    /**
     * Called when the game is paused.
     */
    @Override
    public void pause() {}

    /**
     * Called when the game is resumed after being paused.
     */
    @Override
    public void resume() {}

    /**
     * Called when another screen replaces this one.
     */
    @Override
    public void hide() {}

    /**
     * Releases the resources
     */
    @Override
    public void dispose() {
        font.dispose();
    }
}
