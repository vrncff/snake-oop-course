package group5.snake.entity;

import group5.snake.controll.Cell;
import group5.snake.controll.Direction;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Snake class.
 */
public class SnakeTest {

		/**
		 * Tests the behavior of the Snake's grow() method.
		 */
		@Test
		public void testMultipleGrowCalls() {
				Snake snake = new Snake(5, 5);
				int initialLength = snake.getBody().size();

				snake.grow();
				snake.update();
				snake.grow();
				snake.update();
				snake.grow();
				snake.update();

				assertEquals(initialLength + 3, snake.getBody().size());
		}

		/**
		 * Tests the behavior of the Snake's increaseSpeed() method.
		 */
		@Test
		public void testMultipleSpeedIncreases() {
				Snake snake = new Snake(5, 5);
				float initialSpeed = snake.getSpeed();

				snake.increaseSpeed();
				snake.increaseSpeed();
				snake.increaseSpeed();

				assertTrue(snake.getSpeed() < initialSpeed);
		}

		/**
		 * Tests the initial position of the snake.
		 */
		@Test
		public void testSnakeInitialPosition() {
				Snake snake = new Snake(5, 5);

				assertEquals(5, snake.getBody().getFirst().x);
				assertEquals(5, snake.getBody().getFirst().y);
		}

		/**
		 * Tests the snake's movement in the upward direction.
		 */
		@Test
		public void testSnakeMovementUp() {
				Snake snake = new Snake(5, 5);
				snake.changeDirection(Direction.UP);

				snake.update();

				assertEquals(5, snake.getBody().getFirst().x);
				assertEquals(6, snake.getBody().getFirst().y);
		}

		/**
		 * Tests the snake's movement in the downward direction.
		 */
		@Test
		public void testSnakeMovementDown() {
				Snake snake = new Snake(5, 5);
				snake.changeDirection(Direction.DOWN);

				snake.update();

				assertEquals(5, snake.getBody().getFirst().x);
				assertEquals(4, snake.getBody().getFirst().y);
		}

		/**
		 * Tests the snake's movement in the right direction.
		 */
		@Test
		public void testSnakeMovementRight() {
				Snake snake = new Snake(5, 5);

				snake.changeDirection(Direction.RIGHT);
				snake.update();

				assertEquals(6, snake.getBody().getFirst().x);
				assertEquals(5, snake.getBody().getFirst().y);
		}

		/**
		 * Tests the snake's movement in the left direction.
		 */
		@Test
		public void testSnakeMovementLeft() {
				Snake snake = new Snake(5, 5);

				snake.changeDirection(Direction.UP);
				snake.update(); // Move snake up

				snake.changeDirection(Direction.LEFT);
				snake.update(); // Move snake left

				assertEquals(4, snake.getBody().getFirst().x);
				assertEquals(6, snake.getBody().getFirst().y);
		}

		/**
		 * Tests the snake's movement into opposite direction given that right is the default.
		 */
		@Test
		public void testSnakeDoesntTurnIntoItself() {
				Snake snake = new Snake(5, 5);
				int initialX = snake.getBody().getFirst().x;
				int initialY = snake.getBody().getFirst().y;

				snake.changeDirection(Direction.LEFT);
				snake.update(); 

				assertEquals(initialX + 1, snake.getBody().getFirst().x);
				assertEquals(initialY, snake.getBody().getFirst().y);
		}

		/**
		 * Tests that the snake does not collide with itself initially.
		 */
		@Test
		public void testNoInitialSelfCollision() {
				Snake snake = new Snake(5, 5);
				assertFalse(snake.checkSelfCollision());
		}

		/**
		 * Tests that the snake detects self-collision after moving in a circle.
		 */
		@Test
		public void testSelfCollisionAfterMovingInCircle() {
				Snake snake = new Snake(5, 5);
				snake.grow();
				snake.update();

				snake.grow();
				snake.update();

				snake.grow();
				snake.update();

				snake.changeDirection(Direction.DOWN);
				snake.update();

				snake.changeDirection(Direction.LEFT);
				snake.update();

				snake.changeDirection(Direction.UP);
				snake.update();

				assertTrue(snake.checkSelfCollision());
		}

		/**
		 * Tests that the snake detects collision with another snake.
		 */
		@Test
		public void testCollisionWithAnotherSnake() {
				Snake snake1 = new Snake(5, 5);
				Snake snake2 = new Snake(6, 5);

				assertTrue(snake1.collidesWith(snake2));
		}
}
