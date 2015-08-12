/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuukka
 */
public class Test {
	
	public static void main(String[] args) {
		int[] taulukko = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		char[] taulukko2 = {'a', 'a', 'a', 'a', 'a', 'a', 'a'};
		//boolean testi = taulukko[9] != 10;
		boolean testi2 = taulukko[-1] != ' ';
		System.out.println("Vertailutesti ulkopuolelta " + testi2);
	}
	
}
