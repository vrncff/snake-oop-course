package group5.snake.controll;

/**
 * The Direction enum represents the four possible directions in the game.
 * Each direction is associated with a change in the x or y coordinate.
 */
public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public final int dx;                // Variação de movimento quando movido na direção x
    public final int dy;                // Variação de movimento quando movido na direção y

    /**
     * Constructor for the Direction enum.
     * Initializes the direction with the given changes in the x and y coordinates.
     * @param dx the change in the x-coordinate
     * @param dy the change in the y-coordinate
     */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
