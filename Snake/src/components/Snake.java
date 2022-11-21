package components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
	
	private LinkedList<Integer> xCoordinates = new LinkedList<>();
	
	private LinkedList<Integer> yCoordinates = new LinkedList<>();
	
	private int snakeBody = 1;
	
	private int numberOfApplesEaten = 0;
	
	private char direction;
	
	public void drawSnake(Graphics graphics, int unitSize) {
		if (snakeBody == 1) {
			graphics.setColor(new Color(119, 156, 16));
			graphics.fillRect(xCoordinates.getFirst(), yCoordinates.getFirst(), unitSize, unitSize);
		} else {
			for (int i=0; i<this.snakeBody; i++) {
				if (i==0) {
					graphics.setColor(new Color(119, 156, 16));
					graphics.fillRect(getSnakeHeadX(), getSnakeHeadY(), unitSize, unitSize);
				} else {
					graphics.setColor(new Color(45, 100, 0));
					graphics.fillRect(xCoordinates.get(i), yCoordinates.get(i), unitSize, unitSize);
				}
			}
		}
	}
	
	public void setSnakePartByIndex(int index, char xy, Integer coordinate) {
		if (xy == 'x') {
			xCoordinates.set(index, coordinate);
		} else {
			yCoordinates.set(index, coordinate);
		}
	}
	
	public void reset() {
		xCoordinates = new LinkedList<>();
		yCoordinates = new LinkedList<>();
		setSnakeBody(1);
		setNumberOfApplesEaten(0);
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public int getNumberOfApplesEaten() {
		return numberOfApplesEaten;
	}

	public void setNumberOfApplesEaten(int numberOfApplesEaten) {
		this.numberOfApplesEaten = numberOfApplesEaten;
	}

	public int getSnakeBody() {
		return snakeBody;
	}

	public LinkedList<Integer> getxCoordinates() {
		return xCoordinates;
	}

	public LinkedList<Integer> getyCoordinates() {
		return yCoordinates;
	}

	public void setSnakeBody(int snakeBody) {
		this.snakeBody = snakeBody;
	}
	
	public Integer getSnakePartByIndex(int index, char xy) {
		return (xy == 'x') 
				? xCoordinates.get(index) 
				: yCoordinates.get(index);
	}
	
	public Integer getSnakeHeadX() {
		return xCoordinates.getFirst();
	}
	
	public Integer getSnakeHeadY() {
		return yCoordinates.getFirst();
	}
	
	public Integer getSnakeTailX() {
		return xCoordinates.getLast();
	}
	
	public Integer getSnakeTailY() {
		return yCoordinates.getLast();
	}

	public void setSnakeHeadCoordinates(Integer x, Integer y) {
		xCoordinates.addFirst(x);
		yCoordinates.addFirst(y);
	}

	public void addXCoordinate(Integer x) {
		xCoordinates.add(x);
	}

	public void addYCoordinate(Integer y) {
		yCoordinates.add(y);
	}

}
