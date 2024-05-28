package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Snake {
    private Sprite snake;                       // Sprite que representa a cobra
    private float posX, posY;                   // Posições X e Y da cobra
    private float rotationAngle;                // Ângulo de rotação da cobra
    private final float speed = 200;            // Velocidade em pixels por segundo
    private int lookingAt;                      // Direção que a cobra está olhando: 1 para direita, 2 para baixo, 3 para esquerda, 4 para cima
    private int score;                          // Pontuação do jogador

    // Construtor que inicializa a cobra com a textura fornecida
    public Snake(Texture texture) {
        snake = new Sprite(texture);
        posX = 0;                               // Posição inicial X
        posY = 0;                               // Posição inicial Y
        lookingAt = 1;                          // Inicialmente olhando para a direita
        rotationAngle = 0;                      // Ângulo de rotação inicial
        score = 0;                              // Pontuação inicial
    }

    // Método para movimentar a cobra
    public void move() {
        float deltaTime = Gdx.graphics.getDeltaTime(); // Tempo decorrido desde o último quadro

        // Atualiza a direção e o ângulo de rotação com base na entrada do usuário
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            rotationAngle = 0; // Olha para a direita
            lookingAt = 1;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            rotationAngle = 180; // Olha para a esquerda
            lookingAt = 3;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            rotationAngle = 90; // Olha para cima
            lookingAt = 4;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            rotationAngle = 270; // Olha para baixo
            lookingAt = 2;
        }

        // Movimenta na direção atual
        switch (lookingAt) {
            case 1:
                posX += speed * deltaTime; // Movimenta para a direita
                break;
            case 2:
                posY -= speed * deltaTime; // Movimenta para baixo
                break;
            case 3:
                posX -= speed * deltaTime; // Movimenta para a esquerda
                break;
            case 4:
                posY += speed * deltaTime; // Movimenta para cima
                break;
        }

        // Verifica se a nova posição está dentro dos limites da tela
        if (posX < 0) posX = 0;
        if (posX > Gdx.graphics.getWidth() - snake.getWidth()) posX = Gdx.graphics.getWidth() - snake.getWidth();
        if (posY < 0) posY = 0;
        if (posY > Gdx.graphics.getHeight() - snake.getHeight()) posY = Gdx.graphics.getHeight() - snake.getHeight();
    }

    // Método para desenhar a cobra na tela
    public void draw(SpriteBatch batch) {
        snake.setPosition(posX, posY);                  // Define a posição da cobra
        snake.setRotation(rotationAngle);               // Define o ângulo de rotação da cobra
        snake.draw(batch);                              // Desenha a cobra usando o SpriteBatch
    }

    // Método para verificar colisão com uma maçã
    public void checkCollision(Apple apple) {
        if (apple.isColliding(snake)) {
            score++;                                    // Incrementa a pontuação
            apple.respawn();                            // Reposiciona a maçã
        }
    }

    // Método para liberar os recursos da cobra quando não forem mais necessários
    public void dispose() {
        snake.getTexture().dispose();                   // Libera a textura da memória
    }

    // Método para obter a pontuação atual
    public int getScore() {
        return score;
    }
}
