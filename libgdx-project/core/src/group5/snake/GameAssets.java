package group5.snake;

import com.badlogic.gdx.assets.AssetManager;
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
    }

    public void finishLoading() {
        assetManager.finishLoading();
    }

    public Texture getTexture(String name) {
        return assetManager.get(name, Texture.class);
    }

    public void dispose() {
        assetManager.dispose();
    }
}

