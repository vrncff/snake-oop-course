package group5.snake;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The DesktopLauncher class is the entry point of the game when run on a desktop platform.
 * It sets up the configuration for the game window and launches the game.
 */
public class DesktopLauncher {

	/**
	 * The main method that is called when the application is started.
	 * @param arg command line arguments
	 */
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);									// Frame rate
		config.setTitle("Snake Game");									// Título da janela do jogo
		config.setWindowIcon("icon.png");								// ícone da janela do jogo
		config.setWindowedMode(1200,800);					// Dimensões da janela
		config.setResizable(false);										// Impede que a janela seja redimensionada

		new Lwjgl3Application(new SnakeGame(), config);					// Inicializa o jogo
	}
}
