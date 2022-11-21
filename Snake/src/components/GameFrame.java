package components;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2220038660303338524L;
	
	public GameFrame(int height, int width, int timeInMinutes) {
		this.add(new GameSpace(height, width, timeInMinutes));
		this.setSize(width, height);
		this.setTitle("Play Snake!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		// to have the frame fit the gamespace inside
		this.pack();
		this.setVisible(true);
	}

}
