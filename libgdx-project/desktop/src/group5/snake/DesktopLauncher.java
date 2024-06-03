package group5.snake;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);						// Frame rate
		config.setTitle("Snake Game");						// Título da janela do jogo
		//config.setWindowIcon("icon.png");					// ícone da janela do jogo
		config.setWindowedMode(1200,800);					// Dimensões da janela
		config.setResizable(false);							// Impede que a janela seja redimensionada

		new Lwjgl3Application(new SnakeGame(), config);		// Inicializa o jogo
	}
}
