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
 * Main class of Chess-game "Shakki".
 * @author Tuukka Paukkunen
 */

public class Main {
	
	/**
	 * This method initiates the game.
	 * @param args Command line arguments, not used.
	 */
	public static void main(String[] args) {
		Chessboard chessboard = new Chessboard();
		AI ai = new AI();
		Scanner input = new Scanner(System.in);
		String command;
		boolean continueGame = true;
		UserInterface.draw(chessboard.getBoard());
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
					Move move = null;
					try {
						move = ai.minimax(chessboard, 5, true);
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
						System.out.println("Tietokoneen siirtovuorolla tapahtui virhe.");
					}
					UserInterface.draw(chessboard.getBoard());
				} else {
					System.out.println("Virheellinen komento.");
				}
			}
		} while (continueGame);
	}

}
