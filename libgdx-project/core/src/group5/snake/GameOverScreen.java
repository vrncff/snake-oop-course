package group5.snake;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;

public class GameOverScreen implements Screen {
    final SnakeGame game;
    OrthographicCamera camera;
    BitmapFont font;

    public GameOverScreen(final SnakeGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        font = new BitmapFont();
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
        font.draw(game.batch, "Game Over!", 350, 240);
        font.draw(game.batch, "Press Enter to Restart", 320, 200);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Keys.ENTER)) {
            game.setScreen(new GameScreen(game));
            dispose();
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
        font.dispose();
    }
}
