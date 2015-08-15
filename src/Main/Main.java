package Main;


import User.UserMovement;
import AI.AI;
import AI.Movement;
import UI.UserInterface;
import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
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
	public static void main(String[] args) throws CloneNotSupportedException {
		Chessboard chessboard = new Chessboard();
		AI ai = new AI();
		Scanner input = new Scanner(System.in);
		String command;
		boolean continueGame = true;
		UserInterface.draw(chessboard.getBoard());
		//Movement move = ai.minimax(chessboard, 4, true);
		//System.out.println("Shakki? " + chessboard.isItCheck(true));
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
				if (UserMovement.movePiece(command, chessboard)) {
					UserInterface.draw(chessboard.getBoard());
					System.out.println("Tietokone tekee siirtonsa...");
					Movement move = ai.minimax(chessboard, 3, true);
					System.out.println("Minimax laskettu, ei siirretty:");
					UserInterface.draw(chessboard.getBoard());
					if (!chessboard.movePiece(move.getStart(), move.getEnd())) {
						System.out.println("Siirrossa tapahtui virhe.");
					}
					UserInterface.draw(chessboard.getBoard());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
	}

}
