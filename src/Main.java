
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
		Chessboard chessboard = new Chessboard();
		Scanner input = new Scanner(System.in);
		String command;
		boolean continueGame = true;
		//UserInterface.draw(chessboard.getBoard());
		char[][] newboard = {
				{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{' ', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', 'K', ' ', 'b', ' ', 'R'},
				{' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
				{'R', ' ', 'B', 'Q', ' ', 'B', 'N', ' '}
			};
		chessboard.setBoard(newboard);
		UserInterface.draw(chessboard.getBoard());
		System.out.println("Shakki? " + chessboard.isItCheck(true));
		/*System.out.println("Peli vastaanottaa siirtokomennot koordinaatteina.\n"
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
				if (UserMovement.movePiece(command, chessboard)) {
					UserInterface.draw(chessboard.getBoard());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);*/
	}

}
