package Main;


import User.UserMovement;
import AI.AI;
import AI.Move;
import UI.UserInterface;
import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class of Chess-game "Shakki". Run this to start the game.
 * 
 * Fields:
 * 
 * - Chessboard-object: the board to be played with.
 * - AI-object. Represents the artificial intelligence playing the game.
 * - Scanner, reads user input.
 * - Depth, integer, how many moves ahead the AI calculates.
 * 
 * @author Tuukka Paukkunen
 */
public class Main {
	
	private static Chessboard chessboard;
	private static AI ai;
	private static Scanner input;
	private static int depth;
	
	/**
	 * Initiates the class fields.
	 */
	public static void init() {
		char[][] newboard = {
			{' ', ' ', ' ', ' ', 'k', ' ', ' ', ' '},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{' ', ' ', ' ', ' ', 'K', ' ', ' ', ' '}
		};
		chessboard = new Chessboard(newboard);
		ai = new AI();
		input = new Scanner(System.in);
		depth = 3;
	}
	
	/**
	 * This method initiates the game.
	 * @param args Command line arguments, not used.
	 */
	public static void main(String[] args) {
		String command;
		boolean continueGame = true;

		init();

		//chessboard.setBoard(newboard);
		
		UserInterface.draw(chessboard.getBoardAsCharArray());
		System.out.println("Peli vastaanottaa siirtokomennot koordinaatteina.\n"
				+ "Esimerkiksi komento c2c4 siirtää koordinaatissa c2 olevan\n"
				+ "nappulan koordinaattiin c4. Peli tarkistaa, etteivät\n"
				+ "koordinaatit ole laudan ulkopuolella ja että siirtokomento\n"
				+ "on shakin sääntöjen mukainen.");
		do {
			System.out.println("\nAnna siirtokomento (\"stop\" = lopeta):");
			command = input.nextLine();
			if (command.equals("stop")) {
				continueGame = false;
			} else {
				if (UserMovement.movePiece(command, chessboard)) {
					UserInterface.draw(chessboard.getBoardAsCharArray());
					System.out.println("\nOdota. Tietokone tekee siirtonsa...");
					Move move = null;
					move = ai.getMove(chessboard, depth);
			
					if (!chessboard.movePiece(move)) {
						System.out.println("\n\n\nTietokoneen siirtovuorolla tapahtui virhe."
								+ "Peli lopetetaan.");
						System.out.println("Siirtokomento koordinaatista " + move.getStart() + 
								" koordinaattiin " + move.getEnd());
						ArrayList<Piece> pieces = chessboard.getPieces(false);
						System.out.println("Mustat nappulat ovat:");
						for (Piece piece : pieces) {
							System.out.println(piece.getSign() + " paikassa " + piece.getPosition());
						}
						continueGame = false;
					}
					UserInterface.draw(chessboard.getBoardAsCharArray());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
	}

}
