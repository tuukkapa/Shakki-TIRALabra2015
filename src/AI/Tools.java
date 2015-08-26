/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package AI;

import java.util.Random;

public class Tools {
	
	private static Random random;
	
	public static int randInt(int variance) {
		int min = -1 * variance;
		int max = variance;
		random = new Random();
		int randomNum = random.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
