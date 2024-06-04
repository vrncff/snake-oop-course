package group5.snake;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class manages the game assets such as textures and sounds.
 */
public class GameAssets {
    private AssetManager assetManager;

    /**
     * Constructor for the GameAssets class.
     * Initializes the asset manager.
     */
    public GameAssets() {
        assetManager = new AssetManager();
    }

    /**
     * Loads all the textures and sounds into the asset manager.
     */
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

    /**
     * Blocks until all assets are loaded.
     */
    public void finishLoading() {
        assetManager.finishLoading();
    }

    /**
     * Retrieves a texture from the asset manager.
     * @param name the file name of the texture
     * @return the texture
     */
    public Texture getTexture(String name) {
        return assetManager.get(name, Texture.class);
    }

    /**
     * Retrieves a music asset from the asset manager.
     * @param name the file name of the music asset
     * @return the music asset
     */
    public Music getMusic(String name) {
        return assetManager.get(name, Music.class);
    }

    /**
     * Retrieves a sound asset from the asset manager.
     * @param name the file name of the sound asset
     * @return the sound asset
     */
    public Sound getSound(String name) {
        return assetManager.get(name, Sound.class);
    }

    /**
     * Disposes of all assets in the asset manager.
     */
    public void dispose() {
        assetManager.dispose();
    }
}

