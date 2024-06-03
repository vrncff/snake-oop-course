package group5.snake;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.Random;

public class GameScreen implements Screen {
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

    public GameScreen(final SnakeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        backgroundTexture = game.assets.getTexture("gameScreen.png");
        snakeTexture = game.assets.getTexture("snake.png");
        foodTexture = game.assets.getTexture("food.png");
        snake = new Snake();
        random = new Random();
        spawnFood();
        score = 0;
        font = new BitmapFont();
        started = false; // Inicialmente, o jogo não começou

        soundtrack = game.assets.getMusic("soundtrack.mp3");
        pickUpSound = game.assets.getSound("pickup.wav");
        hitSound = game.assets.getSound("hit.wav");

        soundtrack.setLooping(true);        // Inicia a musica de fundo
        soundtrack.play();

    }

    private void spawnFood() {
        int x = random.nextInt(40);
        int y = random.nextInt(22);     // -2 desconsiderando a faixa de score points
        food = new Food(foodTexture, x, y);
    }

    @Override
    public void show() {
        // Chamado quando esta tela se torna a tela atual do jogo.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, 800, 480);
        for (Cell cell : snake.getBody()) {
            game.batch.draw(snakeTexture, cell.x * 20, cell.y * 20, 20, 20);
        }
        game.batch.draw(food.getTexture(), food.getPosition().x * 20, food.getPosition().y * 20, 20, 20);
        font.draw(game.batch, "Score: " + score, 10, 470);

        game.batch.end();

        if (!started) {
            // Verificar se uma tecla de direção foi pressionada
            if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.RIGHT) ||
                    Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.DOWN)) {
                started = true;
            }
        } else {
            // Atualização do jogo
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
            snake.increaseSpeed(); // Aumentar a velocidade ao comer comida
            spawnFood();
            score += 1; // Incrementar a pontuação
            pickUpSound.play();     // Efeito sonoro de pegar a comida
        }
    }

    @Override
    public void resize(int width, int height) {
        // Chamado quando a tela é redimensionada.
    }

    @Override
    public void pause() {
        // Chamado quando o jogo é pausado.
    }

    @Override
    public void resume() {
        // Chamado quando o jogo é retomado após a pausa.
    }

    @Override
    public void hide() {
        // Chamado quando outra tela substitui esta.
    }

    @Override
    public void dispose() {
        // Dispose do BitmapFont
        font.dispose();

        // Parar a música de fundo se ainda estiver tocando
        if (soundtrack.isPlaying()) {
            soundtrack.stop();
        }
    }
}


