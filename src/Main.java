
import java.util.Scanner;


/**
 * Main class of Chess-game "Shakki".
 * @author Tuukka Paukkunen
 */

public class Main {
	
	/**
	 * This method initiates the game.
	 * @param args 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String command;
		boolean continueGame = true;
		Chessboard.makeTestBoard(false);
		Chessboard.draw();
		System.out.println("Peli vastaanottaa siirtokomennot koordinaatteina.\n"
				+ "Esimerkiksi komento c2c4 siirtää koordinaateissa c2\n"
				+ "olevan nappulan koordinaatteihin c4. Peli tarkistaa, ettei\n"
				+ "koordinaatit ole laudan ulkopuolella ja että siirtokomento\n"
				+ "on Shakin sääntöjen mukainen\n");
		
		do {
			System.out.println("Anna siirtokomento (\"stop\" = lopeta):");
			command = input.nextLine();
			if (command.equals("stop")) {
				continueGame = false;
			} else {
				if (Chessboard.movePiece(command)) {
					Chessboard.draw();
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
		
	}
	
}
