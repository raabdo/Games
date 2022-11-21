package utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import components.GameSpace;

public class SnakeKeyAdapter extends KeyAdapter {
	
	private GameSpace gamePanel;
	
	public SnakeKeyAdapter(GameSpace snakePanel) {
		this.gamePanel = snakePanel;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(gamePanel.getDirection() != 'R' || gamePanel.getSnake().getSnakeBody() == 1) {
				gamePanel.addEntry('L');
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(gamePanel.getDirection() != 'L' || gamePanel.getSnake().getSnakeBody() == 1) {
				gamePanel.addEntry('R');
			}
			break;
		case KeyEvent.VK_UP:
			if(gamePanel.getDirection() != 'D' || gamePanel.getSnake().getSnakeBody() == 1) {
				gamePanel.addEntry('U');
			}
			break;
		case KeyEvent.VK_DOWN:
			if(gamePanel.getDirection() != 'U' || gamePanel.getSnake().getSnakeBody() == 1) {
				gamePanel.addEntry('D');
			}
			break;
		case KeyEvent.VK_ENTER:
			if(!gamePanel.isRunning() || gamePanel.isGameWon()) {
				gamePanel.setRunning(true);
				gamePanel.startGame();
				gamePanel.setStartScreen(false);
			} 
			break;
		}
	}
}
