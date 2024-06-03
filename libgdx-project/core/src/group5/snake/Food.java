package group5.snake;

import com.badlogic.gdx.graphics.Texture;

public class Food {
    private Texture texture;
    private Cell position;

    public Food(Texture texture, int x, int y) {
        this.texture = texture;
        this.position = new Cell(x, y);
    }

    public Cell getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setPosition(int x, int y) {
        this.position.x = x;
        this.position.y = y;
    }
}

