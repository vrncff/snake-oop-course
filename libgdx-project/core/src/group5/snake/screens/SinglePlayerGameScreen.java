package group5.snake.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import group5.snake.*;
import group5.snake.controll.Cell;
import group5.snake.controll.Direction;
import group5.snake.entity.Food;
import group5.snake.entity.Snake;
import java.util.Random;

/**
 * This class represents the main game screen for the Snake game.
 * It handles the rendering of game objects, and user input.
 */
public class SinglePlayerGameScreen implements Screen {
    final SnakeGame game;
    OrthographicCamera camera;
    Texture snakeTexture, foodTexture, backgroundTexture;
    Snake snake;
    Food food;
    float timer;
    Random random;
    int score;
    BitmapFont font;
    boolean started;
    Music soundtrack;
    Sound pickUpSound, hitSound;

    /**
     * Constructor for the SinglePlayerGameScreen class.
     * Initializes the game, camera, textures, sounds, game objects, score, and background music.
     * @param game the current game instance
     */
    public SinglePlayerGameScreen(final SnakeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);     // Define a resolução da tela

        // Carrega as texturas
        backgroundTexture = game.assets.getTexture("gameScreen.png");
        snakeTexture = game.assets.getTexture("snake.png");
        foodTexture = game.assets.getTexture("food.png");

        // Carrega os sons
        soundtrack = game.assets.getMusic("soundtrack.mp3");
        pickUpSound = game.assets.getSound("pickup.wav");
        hitSound = game.assets.getSound("hit.wav");

        // Cria os objetos do jogo
        snake = new Snake(5,5);
        random = new Random();
        font = new BitmapFont();

        score = 0;                          // Inicializa o score = 0
        started = false;                    // Inicialmente, o jogo não começa
        spawnFood();                        // Cria a comida no jogo

        // Configura e inicia a música de fundo
        soundtrack.setLooping(true);
        soundtrack.play();

    }

    /**
     * Spawns food at a random location within the game area.
     */
    private void spawnFood() {
        int x = random.nextInt(40);     // 800/20 = 40 células por grade
        int y = random.nextInt(22);     // 480/20 = 24 (-2 desconsiderando a faixa de score)
        food = new Food(foodTexture, x, y);
    }

    /**
     * Renders the game objects and handles game logic.
     * @param delta time since last frame
     */
    @Override
    public void render(float delta) {

        camera.update();                                            // Atualiza a câmera
        game.batch.setProjectionMatrix(camera.combined);            // Define a matriz de projeção para a câmera

        game.batch.begin();     // Inicia a renderização

        // Desenha o fundo
        game.batch.draw(backgroundTexture, 0, 0, 800, 480);

        // Desenha a cobra
        for (Cell cell : snake.getBody())
            game.batch.draw(snakeTexture, cell.x * 20, cell.y * 20, 20, 20);

        // Desenha a comida
        game.batch.draw(food.getTexture(), food.getPosition().x * 20, food.getPosition().y * 20, 20, 20);

        // Desenha o score board
        font.draw(game.batch, "Score: " + score, 10, 470);

        game.batch.end();       // Finaliza a renderização

        if (!started) {
            // Verificar se uma tecla de direção foi pressionada
            if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT) ||
                    Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.DOWN)) {
                started = true;
            }
        } else {
            // Atualiza o estado do jogo
            timer += delta;
            if (timer >= snake.getSpeed()) {
                snake.update();
                checkCollisions();
                timer = 0;
            }

            // Controle da cobra
            if (Gdx.input.isKeyPressed(Keys.LEFT)) snake.changeDirection(Direction.LEFT);
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) snake.changeDirection(Direction.RIGHT);
            if (Gdx.input.isKeyPressed(Keys.UP)) snake.changeDirection(Direction.UP);
            if (Gdx.input.isKeyPressed(Keys.DOWN)) snake.changeDirection(Direction.DOWN);
        }
    }

    /**
     * Checks for collisions between the snake and the screen borders, the snake itself, and the food.
     * Handles game over condition and plays appropriate sound effects.
     */
    private void checkCollisions() {
        Cell head = snake.getBody().getFirst();

        // Checar colisão com as bordas da tela
        if (head.x < 0 || head.x >= 40 || head.y < 0 || head.y >= 22) {
            game.setScreen(new GameOverScreen(game));
            hitSound.play();            // Efeito sonoro de colisão
            soundtrack.stop();          // Para a musica no game over
            dispose();
        }

        // Checar colisão com a própria cobra
        if (snake.checkSelfCollision()) {
            game.setScreen(new GameOverScreen(game));
            hitSound.play();            // Efeito sonoro de colisão
            soundtrack.stop();          // Para a musica no game over
            dispose();
        }

        // Checar colisão com a comida
        if (head.x == food.getPosition().x && head.y == food.getPosition().y) {
            snake.grow();
            snake.increaseSpeed();      // Aumentar a velocidade ao comer comida
            spawnFood();
            score += 1;                 // Incrementar a pontuação
            pickUpSound.play();         // Efeito sonoro de pegar a comida
        }
    }

    /**
     * Called when this screen becomes the current screen of the game.
     */
    @Override
    public void show() { }

    /**
     * Called when the screen is resized.
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(int width, int height) { }

    /**
     * Called when the game is paused.
     */
    @Override
    public void pause() { }

    /**
     * Called when the game is resumed after being paused.
     */
    @Override
    public void resume() { }

    /**
     * Called when another screen replaces this one.
     */
    @Override
    public void hide() { }

    /**
     * Releases the resources
     */
    @Override
    public void dispose() {
        // Dispose do BitmapFont
        font.dispose();

        // Para a música de fundo se ainda estiver tocando
        if (soundtrack.isPlaying()) {
            soundtrack.stop();
        }
    }
}
