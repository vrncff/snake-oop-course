package group5.snake;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> body;
    private Direction direction;
    private boolean grow;
    private float speed;

    public Snake() {
        body = new LinkedList<>();
        direction = Direction.RIGHT;
        grow = false;
        speed = 0.18f; // Velocidade inicial
        // Adicionar segmentos iniciais
        body.add(new Cell(5, 5));
        body.add(new Cell(4, 5));
    }

    public void update() {
        // Lógica para mover a cobra
        Cell head = body.getFirst();
        int newX = head.x + direction.dx;
        int newY = head.y + direction.dy;
        body.addFirst(new Cell(newX, newY));
        if (!grow) {
            body.removeLast();
        } else {
            grow = false;
        }
    }

    public void grow() {
        // Sinalizar para crescer na próxima atualização
        grow = true;
    }

    public void increaseSpeed() {
        speed *= 0.97f; // Aumenta a velocidade (reduz o intervalo de atualização)
    }

    public float getSpeed() {
        return speed;
    }

    public void changeDirection(Direction newDirection) {
        // Evitar que a cobra volte sobre si mesma
        if (newDirection.dx != -direction.dx && newDirection.dy != -direction.dy) {
            direction = newDirection;
        }
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    public boolean checkSelfCollision() {
        Cell head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            if (head.x == body.get(i).x && head.y == body.get(i).y) {
                return true;
            }
        }
        return false;
    }
}

