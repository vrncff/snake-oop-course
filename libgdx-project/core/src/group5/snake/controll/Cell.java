package group5.snake.controll;

/**
 * The Cell class represents a cell in the game grid.
 * It is used to represent the position of objects in the game, such as the snake's body parts and the food.
 */
public class Cell {
    public int x, y;

    /**
     * Constructor for the Cell class.
     * Initializes the cell with the given x and y coordinates.
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
