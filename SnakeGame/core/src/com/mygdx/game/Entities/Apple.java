package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Apple {
    private Sprite apple;

    // Construtor que inicializa o sprite da maçã com a textura fornecida
    public Apple(Texture texture) {
        apple = new Sprite(texture);
        respawn(); // Coloca a maçã em uma posição aleatória inicial
    }

    // Método para reposicionar a maçã em uma posição aleatória na tela
    public void respawn() {
        float x = MathUtils.random(0, Gdx.graphics.getWidth() - apple.getWidth()); // Posição x aleatória
        float y = MathUtils.random(0, Gdx.graphics.getHeight() - apple.getHeight()); // Posição y aleatória
        apple.setPosition(x, y); // Define a nova posição da maçã
    }

    // Método para desenhar a maçã usando um SpriteBatch
    public void draw(SpriteBatch batch) {
        apple.draw(batch); // Desenha a maçã na tela
    }

    // Verifica se a maçã está colidindo com outro sprite
    public boolean isColliding(Sprite sprite) {
        return apple.getBoundingRectangle().overlaps(sprite.getBoundingRectangle()); // Retorna true se os retângulos delimitadores se sobrepõem
    }

    // Libera os recursos da textura da maçã quando não for mais necessário
    public void dispose() {
        apple.getTexture().dispose(); // Descarrega a textura da memória
    }
}
