package group5.snake.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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
    Texture backgroundTexture;
    Sound selecting, selected;
    BitmapFont font;

    private int selectedOption = 0; // 0 - Single Player, 1 - Multiplayer, 2 - Quit
    private final String[] options = {"Single Player", "Multiplayer", "Quit"};

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

        // Carrega a textura de fundo
        backgroundTexture = game.assets.getTexture("homeScreen.png");

        // Carrega os sons
        selecting = game.assets.getSound("selecting.wav");
        selected = game.assets.getSound("selected.wav");

        // Carrega a fonte para renderizar o texto
        font = new BitmapFont();
    }

    /**
     * Renders the home screen and checks for user input to start the game.
     * @param delta time since last frame
     */
    @Override
    public void render(float delta) {
        camera.update(); // Atualiza a câmera
        game.batch.setProjectionMatrix(camera.combined); // Define a matriz de projeção da câmera

        game.batch.begin(); // Inicia a renderização
        game.batch.draw(backgroundTexture,0,0,800,480);     // Desenha o fundo

        // Renderiza as opções do menu
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                font.setColor(Color.RED);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(game.batch, options[i], 550, 300 - i * 40);
        }

        game.batch.end(); // Termina a renderização

        // Verifica entrada do usuário para navegação no menu
        if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
            selectedOption = (selectedOption - 1 + options.length) % options.length;
            selecting.play();
        }
        if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % options.length;
            selecting.play();
        }

        // Verifica entrada do usuário para seleção de opção
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            selectOption();
            selected.play();
        }
    }

    /**
     * Handles the selection of a menu option.
     */
    private void selectOption() {
        switch (selectedOption) {
            case 0:
                game.setScreen(new SinglePlayerGameScreen(game));
                break;
            case 1:
                game.setScreen(new MultiplayerGameScreen(game));
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
        dispose();
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
