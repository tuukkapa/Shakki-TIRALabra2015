package Main;


import User.UserMovement;
import AI.*;
import UI.UserInterface;
import Chessboard.*;
import Chessboard.pieces.*;
import java.util.*;

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
		chessboard = new Chessboard();
		ai = new AI();
		input = new Scanner(System.in);
		depth = 2;
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
					try {
						move = ai.getMove(chessboard, depth);
					} catch (NullPointerException ne) {
						//System.out.println("getcause getmessage: " + ne.getCause().getMessage());
						System.out.println(ne.getStackTrace().toString());
						System.out.println("getmessage: " + ne.getMessage());
						System.out.println("tostring: " + ne.toString());
					} catch (Exception e) {
						System.out.println("exception: " + e.getCause().getMessage());
					}
					if (!chessboard.movePiece(move)) {
						System.out.println("\n\nTietokoneen siirtovuorolla tapahtui virhe."
								+ "Peli lopetetaan.");
						System.out.println("\nViimeinen siirtokomento oli koordinaatista " + move.getStart() + 
								" koordinaattiin " + move.getEnd());
						System.out.println("\nAlkukoordinaatissa oli nappula: " + chessboard.getSquareContents(move.getStart()));
						ArrayList<Piece> pieces = chessboard.getPieces(false);
						System.out.println("\nMustat nappulat:");
						for (Piece piece : pieces) {
							System.out.println(piece.getSign() + " r" + (piece.getPosition()/8) + " s" + (piece.getPosition()%8));
						}
						continueGame = false;
						System.out.println("\nLauta oli lopussa");
					}
					UserInterface.draw(chessboard.getBoardAsCharArray());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
	}

}
