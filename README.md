## Identificação do Grupo

**Grupo 5**

Oliver Kenzo Kobayashi  - 13676930

Veronica Fernandes Ferreira - 12557355

### Requisitos
JDK 21 ou inferior, compatível com Gradle 8.5 usado pelo wrapper.

O Gradle deve resolver as dependências de LibGDX e JUnit4.

### Instruções de Uso
Simplesmente faça

```sh
./libgdx-project/gradlew run
```

#### Controles
Tela inicial:
- **Setas** ou **WASD** para seleção
- **Enter** para escolha da opção (single player, multiplayer, quit)

Jogo:
- **Enter** para início e reinício do jogo
- **Setas** (e **WASD**) para controle da(s) cobra(s)

#### Lógica de pontuação
O jogo é finalizado ao colidir com as bordas da tela, com o próprio corpo ou com a outra cobra.

##### Single Player
A pontuação obtida é proporcional ao tamanho da cobra, do método `render()` em `SinglePlayerGameScreen`:

```Java
// Checar colisão com a comida
if (head.x == food.getPosition().x && head.y == food.getPosition().y) {
    snake.grow();
    snake.increaseSpeed();      // Aumentar a velocidade ao comer comida
    spawnFood();
    score += snake.getBody().size() + bonus; // Incrementar a pontuação com base no tamanho e bonificação
    bonus += 10;
    pickUpSound.play();         // Efeito sonoro de pegar a comida
}
```

##### Multiplayer
Igual à lógica do single player, mas individual para cada cobra. A diferença está na implementação de penalizações:

Se qualquer uma das cobras colidir com as bordas da tela ou consigo mesma, o jogador correspondente é penalizado:
- Para colisões com as bordas: `score1 -= 50` ou `score2 -= 50`.
- Para colisões consigo mesma: `score1 -= 50` ou `score2 -= 50`.
- Se as cobras colidirem entre si, o jogador que causou a colisão é penalizado: `score1 -= 30` ou `score2 -= 30`.

A cobra com maior pontuação ao fim do jogo é declarada a vencedora.

### Descrição do Projeto

O enunciado está disponível em [Assignment.md](./Assignment.md).

### Comentários Sobre o Código

O código conta com JavaDoc para melhor entendimento das classes e métodos do jogo. Foram usadas as vantagens da biblioteca LibGDX para guiar o desenvolvimento do jogo, em especial a classe `Game`.

### Problemas
Para tomar vantagem da LibGDX, parte da lógica do jogo está aninhada em classes próprias da biblioteca que lidam com gráficos (em especial o método `render()` em `Game`), tornando o setup para realizar testes unitários de difícil entendimento. O grupo não encontrou uma solução a tempo para conseguir realizar os testes unitários neste caso e optou por fazer testes manuais.

### Plano de Teste

Para a classe `Snake.java`, são feitos testes unitários com o JUnit4 conforme [SnakeTest.java](./libgdx-project/core/test/group5/snake/entity/SnakeTest.java):

```Java
/**
 * Tests the behavior of the Snake's grow() method.
 */
@Test
public void testMultipleGrowCalls() {
    // Test implementation
}

/**
 * Tests the behavior of the Snake's increaseSpeed() method.
 */
@Test
public void testMultipleSpeedIncreases() {
    // Test implementation
}

/**
 * Tests the initial position of the snake.
 */
@Test
public void testSnakeInitialPosition() {
    // Test implementation
}

/**
 * Tests the snake's movement in the upward direction.
 */
@Test
public void testSnakeMovementUp() {
    // Test implementation
}

/**
 * Tests the snake's movement in the downward direction.
 */
@Test
public void testSnakeMovementDown() {
    // Test implementation
}

/**
 * Tests the snake's movement in the right direction.
 */
@Test
public void testSnakeMovementRight() {
    // Test implementation
}

/**
 * Tests the snake's movement in the left direction.
 */
@Test
public void testSnakeMovementLeft() {
    // Test implementation
}

/**
 * Tests the snake's movement into opposite direction given that right is the default.
 */
@Test
public void testSnakeDoesntTurnIntoItself() {
    // Test implementation
}

/**
 * Tests that the snake does not collide with itself initially.
 */
@Test
public void testNoInitialSelfCollision() {
    // Test implementation
}

/**
 * Tests that the snake detects self-collision after moving in a circle.
 */
@Test
public void testSelfCollisionAfterMovingInCircle() {
    // Test implementation
}

/**
 * Tests that the snake detects collision with another snake.
 */
@Test
public void testCollisionWithAnotherSnake() {
    // Test implementation
}
````

Esses testes podem ser rodados com

```sh
./libgdx-project/gradlew test
```

e uma página de relatório será gerada em `core/build/reports/tests/test`.

Para demais partes do jogo vinculadas à lógica gráfica e de assets da biblioteca (como nas classes `SinglePlayerGameScreen` e `MultiplayerGameScreen`), foi decidido realizar, por simplicidade de implementação, testes manuais para os comportamentos de

- Spawn da comida
- Consumo de comida e crescimento da cobra
- Colisão com as bordas da tela
- Input do jogador para movimentação da cobra
- Input do jogador para início e reinício do jogo
- Pontuação
- Sons e música

### Resultados dos Testes

Não foram encontrados erros tanto nos testes unitários

| Test                                   | Duration | Result |
|----------------------------------------|----------|--------|
| testCollisionWithAnotherSnake          | 0s       | passed |
| testMultipleGrowCalls                  | 0s       | passed |
| testMultipleSpeedIncreases             | 0s       | passed |
| testNoInitialSelfCollision             | 0s       | passed |
| testSelfCollisionAfterMovingInCircle   | 0s       | passed |
| testSnakeDoesntTurnIntoItself          | 0s       | passed |
| testSnakeInitialPosition               | 0.003s   | passed |
| testSnakeMovementDown                  | 0s       | passed |
| testSnakeMovementLeft                  | 0s       | passed |
| testSnakeMovementRight                 | 0.001s   | passed |
| testSnakeMovementUp                    | 0s       | passed |

quantos nos testes manuais. 
