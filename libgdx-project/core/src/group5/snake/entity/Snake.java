package group5.snake.entity;

import group5.snake.controll.Cell;
import group5.snake.controll.Direction;

import java.util.LinkedList;

/**
 * The Snake class represents the snake in the game.
 * It maintains a list of cells that make up the body of the snake, the direction of movement, and the speed of the snake.
 */
public class Snake {
    private LinkedList<Cell> body;          // Corpo da cobra como uma lista de Cell
    private Direction direction;            // Direção atual da cobra
    private boolean grow;                   // Flag para indicar quando a cobra deve crescer
    private float speed;                    // Velocidade da cobra

    /**
     * Constructor for the Snake class.
     * Initializes the body, direction, growth flag, and speed of the snake.
     */
    public Snake(int x, int y) {
        body = new LinkedList<>();
        direction = Direction.RIGHT;
        grow = false;
        speed = 0.18f; // Velocidade inicial

        // Adicionar segmentos iniciais
        body.add(new Cell(x, y));
        body.add(new Cell(x - 1, y));
    }

    /**
     * Updates the state of the snake.
     * Moves the snake in the current direction and grows the snake if the grow flag is set.
     */
    public void update() {
        // Lógica para mover a cobra
        Cell head = body.getFirst();
        int newX = head.x + direction.dx;
        int newY = head.y + direction.dy;
        body.addFirst(new Cell(newX, newY));
        // Checa se deve crescer
        if (!grow) {
            body.removeLast();
        } else {
            grow = false;
        }
    }

    /**
     * Sets the grow flag to true.
     * The snake will grow the next time it updates.
     */
    public void grow() {
        // Sinalizar para crescer na próxima atualização
        grow = true;
    }

    /**
     * Increases the speed of the snake.
     */
    public void increaseSpeed() {
        this.speed *= 0.97f; // Aumenta a velocidade
    }

    /**
     * Returns the current speed of the snake.
     * @return the speed of the snake
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Changes the direction of the snake.
     * The new direction cannot be opposite to the current direction.
     * @param newDirection the new direction for the snake
     */
    public void changeDirection(Direction newDirection) {
        // Evitar que a cobra volte sobre si mesma
        if (newDirection.dx != -direction.dx && newDirection.dy != -direction.dy) {
            direction = newDirection;
        }
    }

    /**
     * @return the body of the snake
     */
    public LinkedList<Cell> getBody() {
        return body;
    }

    /**
     * Checks if the snake has collided with itself.
     * @return true if the snake has collided with itself, false otherwise
     */
    public boolean checkSelfCollision() {
        Cell head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.x == body.get(i).x && head.y == body.get(i).y) {
                return true;
            }
        }
        return false;
    }

    public boolean collidesWith(Snake other) {
        Cell head = body.getFirst();
        for (Cell cell : other.getBody()) {
            if (head.x == cell.x && head.y == cell.y) {
                return true;
            }
        }
        return false;
    }

}

