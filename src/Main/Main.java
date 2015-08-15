package Main;


import User.UserMovement;
import AI.AI;
import AI.Move;
import UI.UserInterface;
import Chessboard.Chessboard;
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
		chessboard = new Chessboard();
		ai = new AI();
		input = new Scanner(System.in);
		depth = 5;
	}
	
	/**
	 * This method initiates the game.
	 * @param args Command line arguments, not used.
	 */
	public static void main(String[] args) {
		String command;
		boolean continueGame = true;

		init();
		
		UserInterface.draw(chessboard.getBoard());
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
					UserInterface.draw(chessboard.getBoard());
					System.out.println("\nOdota. Tietokone tekee siirtonsa...");
					Move move = null;
					try {
						move = ai.minimax(chessboard, depth, true);
					} catch (CloneNotSupportedException ex) {
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
					} catch (Exception e) {
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
					} finally {
						if (move == null) {
							System.out.println("Siirtokomentoa ei saatu minimax-metodilta.");
							return;
						}
					}
					if (!chessboard.movePiece(move.getStart(), move.getEnd())) {
						System.out.println("Tietokoneen siirtovuorolla tapahtui virhe."
								+ "Peli lopetetaan.");
						continueGame = false;
					}
					UserInterface.draw(chessboard.getBoard());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
	}

}
