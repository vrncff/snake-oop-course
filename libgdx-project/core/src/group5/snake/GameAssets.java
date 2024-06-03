package group5.snake;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GameAssets {
    private AssetManager assetManager;

    public GameAssets() {
        assetManager = new AssetManager();
    }

    public void load() {
        // Carregar todas as texturas e outros recursos aqui
        assetManager.load("snake.png", Texture.class);
        assetManager.load("food.png", Texture.class);
        assetManager.load("gameScreen.png", Texture.class);
        assetManager.load("gameOverScreen.png", Texture.class);
        assetManager.load("soundtrack.mp3", Music.class);
        assetManager.load("pickup.wav", Sound.class);
        assetManager.load("hit.wav", Sound.class);

    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

    public Texture getTexture(String name) {
        return assetManager.get(name, Texture.class);
    }

    public Music getMusic(String name) {
        return assetManager.get(name, Music.class);
    }

    public Sound getSound(String name) {
        return assetManager.get(name, Sound.class);
    }

    public void dispose() {
        assetManager.dispose();
    }
}

