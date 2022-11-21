package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import utils.SnakeKeyAdapter;
import utils.Utils;

public class GameSpace extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6349166485615226873L;
	
	private int screen_width;
	
	private int screen_height;
	 
	private final int gameUnit = 25;
	
	// The time in between event executions we choose 1 second here
	private static final int DELAY = 1000; 
	
	private boolean gameRunning = false;
	
	private boolean isStartScreen = true;
	
	private boolean gameWon = false;

	private Timer timer;
	
	private long startTime;
		
	private int timeLimit;
	
	private Snake snake = new Snake();
	
	private Apple apple;
	
	Random random = new Random();
	
	private int scoreToWin = 15;
		
	private Queue<Character> lastFourKeysPressed = new LinkedList<>();
	
	public GameSpace(int height, int width, int timeInMinutes) {
		random = new Random();
		generateSnakeStartCoordinates();
		apple = new Apple(height, width, gameUnit, snake);
		screen_width = width;
		screen_height = height;
		setTimeLimit(timeInMinutes);
		setPreferredSize(new Dimension(screen_width, screen_height));
		setBackground(Color.white);
		setFocusable(true);
		addKeyListener(new SnakeKeyAdapter(this));
		startGame();
	}

	/**
	 * Registers 
	 * @param keyPressed
	 */
	public void addEntry(Character keyPressed) {
		if (!lastFourKeysPressed.isEmpty() && lastFourKeysPressed.size() >= 4) {
			lastFourKeysPressed.remove();
		}
		lastFourKeysPressed.add(keyPressed);
	}

	/**
	 * The method is fired by the timer every time the delay (1sec) has passed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameRunning) {
			// checks if the time is up
			if (Utils.getTimeNow(startTime) >= timeLimit) {
				setRunning(false);
			}
			// Only runs if the player has pressed some keys
			if (!lastFourKeysPressed.isEmpty()) {
				Integer currentTailX = snake.getSnakeTailX();
				Integer currentTailY = snake.getSnakeTailY();
				move();
				checkApple(currentTailX, currentTailY);
				checkCollision();
			}
		}
		// clears the graphic on the panel and executes the paintComponent
		repaint();
	}

	/**
	 * Generates random coordinates near the center used for spawning the snake
	 */
	private void generateSnakeStartCoordinates() {
		int snake_start_x = screen_width / 2 + Utils.generateRandomNumber(-2, 2, gameUnit);
		int snake_start_y = screen_height / 2 + Utils.generateRandomNumber(-2, 2, gameUnit);		
		snake.setSnakeHeadCoordinates(snake_start_x, snake_start_y);
	}
	
	/**
	 * Starts the game by reseting the snake and the timer
	 */
	public void startGame () {
		initializeSnake();
		timer = new Timer(DELAY, this);
		timer.start();
		startTime = System.currentTimeMillis();
		repaint();
	}

	private void initializeSnake() {
		snake.reset();
		generateSnakeStartCoordinates();
	}

	public void draw(Graphics graphics) {
		if (snake.getNumberOfApplesEaten() >= scoreToWin) {
			winScreen(graphics);
		} else if (gameRunning) {
			apple.drawApple(graphics, gameUnit);
			snake.drawSnake(graphics, gameUnit);
			graphics.setFont( new Font("Ink Free",Font.BOLD, 40));
			graphics.setColor(Color.black);
			FontMetrics metrics = getFontMetrics(graphics.getFont());
			drawScore(graphics, metrics);
		}  else {
			startAndGameOverScreen(graphics);
		}
	}
	
	/**
	 * Paints the panel 
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		draw(graphics);
	}
	
	/**
	 * Checks if the snake touches the apple
	 * Then increments the snake's body, number of apples eaten
	 * Spawns a new apple and adds the tail's coordinates
	 * 
	 * @param tailX
	 * @param tailY
	 */
	public void checkApple(Integer tailX, Integer tailY) {
		if(
			(snake.getSnakeHeadX() == apple.getAppleX())
			&& (snake.getSnakeHeadY() == apple.getAppleY())
		) {
			snake.setSnakeBody(snake.getSnakeBody() + 1);
			snake.setNumberOfApplesEaten(snake.getNumberOfApplesEaten() + 1);
			snake.addXCoordinate(tailX);
			snake.addYCoordinate(tailY);
			setApple(new Apple(screen_height, screen_width, gameUnit, snake));
		}
	}
	
	/**
	 * Collision handling
	 */
	public void checkCollision () {
		// checks if the snakes head collided with the body (only runs if at least 4 apples are eaten)
		if (snake.getSnakeBody() > 4) {
			for(int i = snake.getSnakeBody()-1; i>0; i--) {
				if(
					(snake.getSnakeHeadX().equals(snake.getSnakePartByIndex(i, 'x')))
						&& (snake.getSnakeHeadY().equals(snake.getSnakePartByIndex(i, 'y')))
				) {
					gameRunning = false;
				}
			}
		}	
		//If the snake touches the left border
		if(snake.getSnakeHeadX() < 0) {
			gameRunning = false;
		}
		//If the snake touches the right border
		if(snake.getSnakeHeadX() >= screen_width) {
			gameRunning = false;
		}
		//If the snake touches the top
		if(snake.getSnakeHeadY() < 0) {
			gameRunning = false;
		}
		//If the snake touches the bottom
		if(snake.getSnakeHeadY() >= screen_height) {
			gameRunning = false;
		}
		
		if(!gameRunning) {
			timer.stop();
		}
	}

	/**
	 * Handles the coordinate modification when the snake is moved
	 */
	public void move() {
		for (int i=snake.getSnakeBody()-1; i>0; i--) {
			// Replace the coordinates of each snakeBody part by the coordinates of the one before it
			// Note that we did not modify the head yet
			snake.setSnakePartByIndex(i, 'x', snake.getSnakePartByIndex(i-1, 'x'));			
			snake.setSnakePartByIndex(i, 'y', snake.getSnakePartByIndex(i-1, 'y'));
		} 
		char newDirection = lastFourKeysPressed.remove().charValue();
		snakeMovementDirection(newDirection);
		setDirection(newDirection);
	}

	/**
	 * 
	 * @param dir This methods sets the direction of the snake
	 * 
	 * The snake can move Up, Down, Left or Right
	 * The snake cannot go on the opposite direction once the body is bigger than one unit
	 * For example if the snake has eaten 4 apples and is going up, then it is not possible to go down
	 * 
	 */
	private void snakeMovementDirection(char dir) {
		int headCoordinates;
		switch (dir) {
		case 'U':
			headCoordinates = snake.getSnakeHeadY();
			snake.setSnakePartByIndex(0, 'y', headCoordinates - gameUnit);
			break;
		case 'D':
			headCoordinates = snake.getSnakeHeadY();
			snake.setSnakePartByIndex(0, 'y', headCoordinates + gameUnit);
			break;
		case 'L':
			headCoordinates = snake.getSnakeHeadX();
			snake.setSnakePartByIndex(0, 'x', headCoordinates - gameUnit);
			break;
		case 'R':
			headCoordinates = snake.getSnakeHeadX();
			snake.setSnakePartByIndex(0, 'x', headCoordinates + gameUnit);
			break;
		}
	}
	
	/**
	 * This method is responsible for drawing the score on the top of the panel
	 * @param graphics
	 * @param metrics
	 * 
	 */
	private void drawScore(Graphics graphics, FontMetrics metrics) {
		graphics.drawString(
				"Score: "+ snake.getNumberOfApplesEaten(), 
				(screen_width - metrics.stringWidth("Score: "+ snake.getNumberOfApplesEaten()))/2, 
				graphics.getFont().getSize()
		);
	}
	
	/**
	 * Draws the win message
	 * @param graphics
	 * @param metrics
	 * 
	 */
	private void drawWinMessage(Graphics graphics, FontMetrics metrics) {
		graphics.drawString(
				"You win!",
				(screen_width - metrics.stringWidth("You win!")) / 2, 
				screen_height/3
		);
	}
	
	/**
	 * Draws the message prompting to press enter to start the game
	 * @param graphics
	 * @param metrics
	 * 
	 */
	private void drawPressEnterMessage(Graphics graphics, FontMetrics metrics) {
		graphics.drawString(
				"press enter to play", 
				(screen_width - metrics.stringWidth("press enter to play")) / 2, 
				screen_height/2
		);
	}
	
	/**
	 * Draws the game over message
	 * 
	 * @param graphics
	 * @param metrics
	 */
	private void drawGameOverMessage(Graphics graphics, FontMetrics metrics) {
		graphics.drawString(
				"Game Over!", 
				(screen_width - metrics.stringWidth("Game Over!")) / 2, 
				screen_height/3
			);
	}
	
	/**
	 * displays the win screen
	 * 
	 * @param graphics
	 * @param metrics
	 */
	private void winScreen(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(graphics.getFont());
		drawScore(graphics, metrics);
		drawWinMessage(graphics, metrics);
		drawPressEnterMessage(graphics, metrics);
		gameWon = true;
	}
		
	/**
	 * Displays the game over screen / start screen
	 * 
	 * @param graphics
	 */
	public void startAndGameOverScreen(Graphics graphics) {
		graphics.setFont(new Font("Ink Free",Font.BOLD, 40));
		graphics.setColor(Color.black);
		FontMetrics metrics = getFontMetrics(graphics.getFont());
		//Score
		if (isStartScreen) {
			drawPressEnterMessage(graphics, metrics);
		} else {
			drawScore(graphics, metrics);
			drawGameOverMessage(graphics, metrics);
			drawPressEnterMessage(graphics, metrics);
		}
	}
	
	// Getters and Setters

	public char getDirection() {
		return snake.getDirection();
	}

	public void setDirection(char direction) {
		this.snake.setDirection(direction);;
	}
	
	public boolean isRunning() {
		return gameRunning;
	}

	public void setRunning(boolean running) {
		this.gameRunning = running;
		snake.setNumberOfApplesEaten(0);
	}

	public Apple getApple() {
		return apple;
	}

	public void setApple(Apple apple) {
		this.apple = apple;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public boolean isStartScreen() {
		return isStartScreen;
	}

	public void setStartScreen(boolean isStartScreen) {
		this.isStartScreen = isStartScreen;
	}
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}
	
	public Queue<Character> getLastFourKeysPressed() {
		return lastFourKeysPressed;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

}
