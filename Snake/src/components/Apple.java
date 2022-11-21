package components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple {
	
	private int appleX;

	private int appleY;
        
    Random random = new Random();
	
	public Apple(int height, int width, int unitSize, Snake snake) {
		do {
			generateApple(height, width, unitSize);
		} while (IsAppleOverlaps(snake));
    }
	
	public void drawApple(Graphics graphics, int unitSize) {
		graphics.setColor(Color.red);
		graphics.fillOval(appleX, appleY, unitSize, unitSize);
	}

    public int getAppleX() {
		return appleX;
	}

	public void setAppleX(int appleX) {
		this.appleX = appleX;
	}

	public int getAppleY() {
		return appleY;
	}

	public void setAppleY(int appleY) {
		this.appleY = appleY;
	}
	
	/**
	 * Generates the apple's coordinates which can be anywhere in the game space
	 * 
	 * @param height
	 * @param width
	 * @param unitSize
	 */
	public void generateApple(int height, int width, int unitSize) {
		appleX = random.nextInt((int) width/unitSize) * unitSize;
		appleY = random.nextInt((int) height/unitSize) * unitSize;
	}
	
	/**
	 * Checks if Apple x coordinate is overlapping with any of the snake's body x coordinates
	 * If the value exists on the x coordinates we test if the respective y coordinate is equal 
	 * to the Apple's y coordinate
	 * 
	 * @param snake
	 * @return
	 */
	private boolean IsAppleOverlaps(Snake snake) {
		int index = snake.getxCoordinates().indexOf(appleX);
		if (index != -1) {
			return snake.getSnakePartByIndex(index, 'x').intValue() == appleX
					&& snake.getSnakePartByIndex(index, 'y').intValue() == appleY;
		}
		return false;
	}

}
