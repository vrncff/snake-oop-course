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
        // Assets para o home screen
        assetManager.load("homeScreen.png", Texture.class);
        assetManager.load("selecting.wav", Sound.class);
        assetManager.load("selected.wav", Sound.class);

        // Assets para o game screen
        assetManager.load("snake1.png", Texture.class);
        assetManager.load("snake2.png", Texture.class);
        assetManager.load("food.png", Texture.class);
        assetManager.load("gameScreen.png", Texture.class);
        assetManager.load("soundtrack.mp3", Music.class);
        assetManager.load("pickup.wav", Sound.class);
        assetManager.load("hit.wav", Sound.class);

        // Assets para o game over screen
        assetManager.load("gameOverScreen.png", Texture.class);
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

