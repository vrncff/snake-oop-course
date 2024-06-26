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
public class MultiplayerGameScreen implements Screen {
    private static final int CELL_SIZE = 20;    // Tamanho de cada célula em pixels
    private static final int GRID_WIDTH = 40;   // Número de células na largura (800 / 20)
    private static final int GRID_HEIGHT = 24;  // Número de células na altura (480 / 20)

    final SnakeGame game;
    OrthographicCamera camera;
    Texture snake1Texture, snake2Texture, foodTexture, backgroundTexture;
    Music soundtrack;
    Sound pickUpSound, hitSound;
    Snake snake1, snake2;
    Food food;
    float timer1, timer2;
    Random random;
    int score1, score2;
    int bonus1, bonus2;
    BitmapFont font;
    boolean isGameStarted, isGameOver;

    /**
     * Constructor for the MultiplayerGameScreen class.
     * Initializes the game, camera, textures, sounds, game objects, score, and background music.
     * @param game the current game instance
     */
    public MultiplayerGameScreen(final SnakeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);     // Define a resolução da tela

        // Carrega as texturas
        backgroundTexture = game.assets.getTexture("gameScreen.png");
        snake1Texture = game.assets.getTexture("snake1.png");
        snake2Texture = game.assets.getTexture("snake2.png");
        foodTexture = game.assets.getTexture("food.png");

        // Carrega os sons
        soundtrack = game.assets.getMusic("soundtrack.mp3");
        pickUpSound = game.assets.getSound("pickup.wav");
        hitSound = game.assets.getSound("hit.wav");

        // Cria os objetos do jogo
        snake1 = new Snake(20, 5);
        snake2 = new Snake( 20, 15);

        random = new Random();
        font = new BitmapFont();

        score1 = score2 = 0;                          // Inicializa o score = 0
        bonus1 = bonus2 = 0;                          // Inicializa o bonus = 0

        isGameStarted = false;                        // Inicialmente, o jogo não começa
        isGameOver = false;
        spawnFood();                                    // Cria a comida no jogo

        // Configura e inicia a música de fundo
        soundtrack.setLooping(true);
        soundtrack.play();
    }

    /**
     * Spawns food at a random location within the game area.
     */
    private void spawnFood() {
        boolean validPosition;
        do {
            validPosition = true;
            int x = random.nextInt(GRID_WIDTH);                 // 800/20 = 40 células por grade
            int y = random.nextInt(GRID_HEIGHT - 2);     // 480/20 = 24 (-2 desconsiderando a faixa de score)
            food = new Food(foodTexture, x, y);

            // Verifica se a posição gerada coincide com qualquer parte da cobra
            for (Cell cell : snake1.getBody()) {
                if (cell.x == x && cell.y == y) {
                    validPosition = false;
                    break;
                }
            }
            for (Cell cell : snake2.getBody()) {
                if (cell.x == x && cell.y == y) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
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
        game.batch.draw(backgroundTexture, 0, 0, GRID_WIDTH * CELL_SIZE, GRID_HEIGHT * CELL_SIZE);

        // Desenha a cobra 1
        for (Cell cell : snake1.getBody()) {
            game.batch.draw(snake1Texture, cell.x * CELL_SIZE, cell.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        // Desenha a cobra 2
        for (Cell cell : snake2.getBody()) {
            game.batch.draw(snake2Texture, cell.x * CELL_SIZE, cell.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        // Desenha a comida
        game.batch.draw(food.getTexture(), food.getPosition().x * CELL_SIZE, food.getPosition().y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Desenha o score board
        font.draw(game.batch, "Player 1  - Score: " + score1, 10, 470);
        font.draw(game.batch, "Player 2  - Score: " + score2, 640, 470);

        game.batch.end();       // Finaliza a renderização

        if (!isGameStarted) {
            // Verificar se uma tecla de direção foi pressionada para qualquer uma das cobras
            if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT) ||
                    Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.DOWN) ||
                    Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.D) ||
                    Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.S)) {
                isGameStarted = true;
            }
        } else {
            // Atualiza o estado do jogo
            timer1 += delta;
            timer2 += delta;
            if (timer1 >= snake1.getSpeed()) {
                snake1.update();
                timer1 = 0;
            }
            if(timer2 >= snake2.getSpeed()) {
                snake2.update();
                timer2 = 0;
            }
            checkCollisions();

            // Controle da cobra 1
            if (Gdx.input.isKeyPressed(Keys.W)) snake1.changeDirection(Direction.UP);
            if (Gdx.input.isKeyPressed(Keys.A)) snake1.changeDirection(Direction.LEFT);
            if (Gdx.input.isKeyPressed(Keys.S)) snake1.changeDirection(Direction.DOWN);
            if (Gdx.input.isKeyPressed(Keys.D)) snake1.changeDirection(Direction.RIGHT);

            // Controle da cobra 2
            if (Gdx.input.isKeyPressed(Keys.UP)) snake2.changeDirection(Direction.UP);
            if (Gdx.input.isKeyPressed(Keys.LEFT)) snake2.changeDirection(Direction.LEFT);
            if (Gdx.input.isKeyPressed(Keys.DOWN)) snake2.changeDirection(Direction.DOWN);
            if (Gdx.input.isKeyPressed(Keys.RIGHT)) snake2.changeDirection(Direction.RIGHT);
        }
    }

    /**
     * Checks for collisions between the snake and the screen borders, the snake itself, and the food.
     * Handles game over condition and plays appropriate sound effects.
     */
    private void checkCollisions() {
        Cell head1 = snake1.getBody().getFirst();
        Cell head2 = snake2.getBody().getFirst();
        boolean gameOver = false;

        // Checar colisão com as bordas da tela para ambas as cobras
        if (head1.x < 0 || head1.x >= GRID_WIDTH || head1.y < 0 || head1.y >= GRID_HEIGHT - 2) {
            score1 -= 50;                // Penaliza o jogador 1
            gameOver = true;
        }
        if (head2.x < 0 || head2.x >= GRID_WIDTH || head2.y < 0 || head2.y >= GRID_HEIGHT - 2) {
            score2 -= 50;                // Penaliza o jogador 2
            gameOver = true;
        }

        // Checar colisão das cobras consigo mesmas
        if (snake1.checkSelfCollision()) {
            score1 -= 50;                // Penaliza o jogador 1
            gameOver = true;
        }
        if (snake2.checkSelfCollision()) {
            score2 -= 50;                // Penaliza o jogador 2
            gameOver = true;
        }

        // Checar colisão entre as cobras
        if (snake1.collidesWith(snake2)) {
            score1 -= 30;                // Penaliza o jogador 1
            gameOver = true;
        }else if (snake2.collidesWith(snake1)) {
            score2 -= 30;                // Penaliza o jogador 2
            gameOver = true;
        }

        if (gameOver) {
            game.setScreen(new GameOverScreen(game, score1, score2, true));
            hitSound.play();            // Efeito sonoro de colisão
            soundtrack.stop();          // Para a música no game over
            dispose();
            return;
        }

        // Checar colisão com a comida para ambas as cobras
        if (head1.x == food.getPosition().x && head1.y == food.getPosition().y) {
            snake1.grow();
            snake1.increaseSpeed();                     // Aumentar a velocidade ao comer
            spawnFood();                                // Gera nova comida
            score1 += snake1.getBody().size() + bonus1; // Incrementar a pontuação com base no tamanho e bonificação
            bonus1 += 10;                               // Incrementar a bonificação
            pickUpSound.play();                         // Efeito sonoro de pegar a comida
        }
        if (head2.x == food.getPosition().x && head2.y == food.getPosition().y) {
            snake2.grow();
            snake2.increaseSpeed();                     // Aumentar a velocidade ao comer
            spawnFood();                                // Gera nova comida
            score2 += snake2.getBody().size() + bonus2; // Incrementar a pontuação com base no tamanho e bonificação
            bonus2 += 10;                               // Incrementar a bonificação
            pickUpSound.play();                         // Efeito sonoro de pegar a comida
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


