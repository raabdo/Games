package utils;

import java.util.Random;

public class Utils {
	
	public static long getTimeNow(long startTime) {
		return (System.currentTimeMillis() - startTime) / 60000;
	}
	
	/**
	 * 
	 * @param low
	 * @param high
	 * @param unitSize
	 * @return a random number in between low and high 
	 *  
	 */
	public static int generateRandomNumber(int low, int high, int unitSize) {
		Random r = new Random();
		return (r.nextInt(high-low) + low) * unitSize;
	}
}
