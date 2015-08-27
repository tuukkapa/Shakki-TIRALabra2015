/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package AI;

import java.util.Random;

/**
 * This class is a container of general tools for Evaluate-class.
 * @author tuukka
 */
public class Tools {
	
	private static Random random;
	
	/**
	 * Returns random integer larger or smaller than variance from zero.
	 * @param variance Integer, variance.
	 * @return Integer, random number within variance.
	 */
	public static int randInt(int variance) {
		int min = -1 * variance;
		int max = variance;
		random = new Random();
		int randomNum = random.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
