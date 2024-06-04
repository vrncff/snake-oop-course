package group5.snake.entity;

import com.badlogic.gdx.graphics.Texture;
import group5.snake.controll.Cell;

/**
 * The Food class represents the food in the game.
 * It maintains a texture for the food and the position of the food on the game grid.
 */
public class Food {
    private Texture texture;
    private Cell position;

    /**
     * Constructor for the Food class.
     * Initializes the texture and position of the food.
     * @param texture the texture of the food
     * @param x the x-coordinate of the food's position
     * @param y the y-coordinate of the food's position
     */
    public Food(Texture texture, int x, int y) {
        this.texture = texture;
        this.position = new Cell(x, y);
    }

    /**
     * Returns the position of the food.
     * @return the position of the food
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Returns the texture of the food.
     * @return the texture of the food
     */
    public Texture getTexture() {
        return texture;
    }

}

