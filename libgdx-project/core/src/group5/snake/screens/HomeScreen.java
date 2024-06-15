package group5.snake.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input.Keys;
import group5.snake.SnakeGame;

/**
 * This class represents the home screen for the Snake game.
 * It handles the rendering of the home screen and user input to start the game.
 */
public class HomeScreen implements Screen {
    final SnakeGame game;
    OrthographicCamera camera;
    Texture bgHomeScreen, title;
    BitmapFont font;
    float pulseTimer = 0f;
    float pulseDuration = 1f; // Tempo para uma pulsação completa

    /**
     * Constructor for the HomeScreen class.
     * Initializes the game, camera, background texture, and font.
     * @param game the current game instance
     */
    public HomeScreen(final SnakeGame game) {
        this.game = game;

        // Cria e configura a resolução da tela
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        bgHomeScreen = game.assets.getTexture("bgHomeScreen.png");            // Carrega a textura de fundo
        title = game.assets.getTexture("title.png");            // Carrega a textura de fundo

        // Carrega a fonte para renderizar o texto
        font = new BitmapFont();
    }

    /**
     * Renders the home screen and checks for user input to start the game.
     * @param delta time since last frame
     */
    @Override
    public void render(float delta) {
        pulseTimer += Gdx.graphics.getDeltaTime();
        float pulseProgress = pulseTimer / pulseDuration;
        float scaleFactor = 1 + 0.07f * (1 - (float)Math.cos(Math.PI * 2 * pulseProgress)); // Fator de escala para a pulsação

        camera.update();                                        // Atualiza a câmera
        game.batch.setProjectionMatrix(camera.combined);        // Define a matriz de projeção da câmera

        game.batch.begin();                                                     // Inicia a renderização
        game.batch.draw(bgHomeScreen,0,0);          // Desenha o fundo
        game.batch.draw(title,0,0);
        game.batch.end();                                                       // Termina a renderização

        // Lógica para controlar a animação de pulsação
        if (pulseTimer > pulseDuration) {
            pulseTimer -= pulseDuration;
        }

        // Verifica se a tecla foi pressionada
        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            // Aqui você pode iniciar o jogo ou alternar para outra tela, por exemplo:
            // game.setScreen(new GameScreen(game)); // Supondo que GameScreen seja a tela do jogo
            // dispose(); // Se necessário
        }
    }

    // Implementação dos métodos não utilizados
    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        font.dispose(); // Libera recursos da fonte quando a tela é descartada
    }
}
