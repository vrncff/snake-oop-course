package group5.snake.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

    /**
     * Constructor for the GameOverScreen class.
     * Initializes the game, camera, and background texture.
     * @param game the current game instance
     */
    public GameOverScreen(final SnakeGame game) {
        this.game = game;

        // Cria e configura a resolução da tela
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        backgroundTexture = game.assets.getTexture("gameOverScreen.png");            // Carrega a textura de fundo
    }

    /**
     * Renders the game over screen and checks for user input to restart the game.
     * @param delta time since last frame
     */
    @Override
    public void render(float delta) {

        camera.update();                                        // Atualiza a câmera
        game.batch.setProjectionMatrix(camera.combined);        // Define a matriz de projeção da câmera

        game.batch.begin();                                                     // Inicia a renderização
        game.batch.draw(backgroundTexture,0,0,800,480);     // Desenha o fundo
        game.batch.end();                                                       // Termina a renderização

        // Verifica se a tecla foi pressionada
        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            game.setScreen(new HomeScreen(game));
            dispose();
        }
    }

    /**
     * Called when the screen is resized.
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(int width, int height) {}

    /**
     * Called when this screen becomes the current screen of the game.
     */
    @Override
    public void show() { }

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
     * Called when this screen is disposed of.
     */
    @Override
    public void dispose() {}
}
