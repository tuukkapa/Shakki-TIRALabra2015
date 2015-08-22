package UI;

import AI.AI;
import Chessboard.Chessboard;
import Chessboard.Move;
import User.UserMovement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Static class to draw the user interface.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class UserInterface {
	
	private static Chessboard chessboard;
	private static AI ai;
	private static Scanner input;
	private static String command;
	private static int depth;
	
	public static void runGame() {
		chessboard = new Chessboard();
		ai = new AI();
		input = new Scanner(System.in);
		depth = 0;
		System.out.println("========================================================\n"
						 + "======================== SHAKKI ========================\n"
						 + "============== (c) 2015 Tuukka Paukkunen ===============\n"
						 + "========================================================\n");
		System.out.println("Anna pelipuun syvyys (1-6).\n"
				+ "Syvempi pelipuu tarkoittaa sekä parempaa tietokoneen\n"
				+ "suoriutumista pelistä että pidempiä viiveitä tietokoneen\n"
				+ "siirtovuorolla:");
		while (depth  < 1 || depth > 6) {
			try {
				String depthValue = input.nextLine();
				depth = Integer.parseInt(depthValue);
				if (depth < 1 || depth > 6) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				if (depth > 6) {
					System.out.println("En ole mikään Deep Blue. Anna luku yhdestä kuuteen:");
				} else {
					System.out.println("Syötit pelipuun syvyyden väärin. Anna luku yhdestä kuuteen:");
				}
			}
		}
		System.out.println("\nPeli vastaanottaa siirtokomennot koordinaatteina.\n"
				+ "Esimerkiksi komento c2c4 siirtää koordinaatissa c2 olevan\n"
				+ "nappulan koordinaattiin c4. Peli tarkistaa, etteivät\n"
				+ "koordinaatit ole laudan ulkopuolella ja että siirtokomento\n"
				+ "on shakin sääntöjen mukainen.\n"
				+ "\n"
				+ "Tietokoneen viimeiseksi siirtämä nappula näkyy vihreänä.");
		boolean computersTurnOk = true;
		drawBoard(chessboard.getBoardAsCharArray(), null);
		while (usersTurn() && computersTurnOk) {
			computersTurnOk = computersTurn();
		}
	}
	
	/**
	 * Method handles the UI on the user's turn.
	 * @param chessboard Chessboard-object, which game is played.
	 * @return True, if user wants or can continue the game, false otherwise.
	 */
	private static boolean usersTurn() {
		boolean falseCommand = false;
		do {
			System.out.println("\nAnna siirtokomento (\"stop\" = lopeta):");
			command = input.nextLine();
			if (command.equals("stop")) {
				System.out.println("Poistutaan pelistä.");
				return false;
			} else if (UserMovement.movePiece(command, chessboard)) {
				falseCommand = false; // user gave a valid command
			} else {
				System.out.println("Annoit virheellisen komennon. Yritä uudelleen.");
				falseCommand = true;
			}
		} while (falseCommand);
		drawBoard(chessboard.getBoardAsCharArray(), null);
		return true;
	}
	
	/**
	 * Method handles the UI on the computer's turn.
	 * @param ai AI-object, which determines the computer's next move.
	 * @param chessboard Chessboard-object, which game is played.
	 * @return True, if computer can continue the game, false otherwise.
	 */
	private static boolean computersTurn() {
		System.out.println("\nOdota. Tietokone tekee siirtonsa...");
		Move move = null;
		try {
			move = ai.getMove(chessboard, depth);
		} catch (NullPointerException ne) {
			Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ne);
		} catch (Exception e) {
			Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, e);
		}
		// move from 0 to 0 means black lost the game
		if (move.getStart() == 0 && move.getEnd() == 0) {
			System.out.println("Sinä voitit!");
			return false;
		} else if (chessboard.movePiece(move)) {
			drawBoard(chessboard.getBoardAsCharArray(), move);
			if (chessboard.isItCheckMate() == 1) {
				System.out.println("Tietokone voitti sinut!");
				return false;
			} else {
				return true;
			}
		} else {
			System.out.println("\n\nTietokoneen siirtovuorolla tapahtui virhe."
					+ "Peli lopetetaan.");
			return false;
		}
	}
	
	/**
	 * Method uses the char-array of this class as an input and outputs human
	 * readable ASCII-character version of the chess board.
	 * @param chessboard Two dimensional char-array, the board to be drawn.
	 */
	private static void drawBoard(char[][] chessboard, Move move/*, Chessboard board*/) {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 33; j++) {
				// Draw chess board's top and bottom edge
				if (i == 0 || i == 16) {
					if (j == 0) {
						System.out.print("  ");
					}
					System.out.print("-");
				} else {
					// Draw the horizontal lines between chess board's row of squares
					if (i%2 == 0) {
						if (j == 0 || j == 32) {
							if (j == 0) {
								System.out.print("  ");
							}
							System.out.print("|");
						} else {
							System.out.print("-");
						}
					// Draw one row of chess board squares
					} else {
						// Draw the lines between squaress
						if (j%4 == 0) {
							if (j == 0) {
								// 9-((i-1)/2 + 1) calculates board's rownumber from i
								System.out.print(9-((i-1)/2 + 1) + " |");
							} else {
								System.out.print("|");
							}
						// Draw the contents of the square itself
						} else {
							if ((j-2)%4 == 0) {
								/*
								    i-1/2 equals chessboard's equivalent rownumber
								    j-2/4 equals chessboard's equivalent colnumber
								*/
								if (move != null && move.getEnd()/8 == (i-1)/2 && move.getEnd()%8 == (j-2)/4) {
									System.out.print("\033[32m" + chessboard[(i-1)/2][(j-2)/4] + "\033[0m");
								} else {
									System.out.print(chessboard[(i-1)/2][(j-2)/4]);
								}
							} else {
								System.out.print(" ");
							}							
						}
					}
				}
			}
			if (i == 1) {
				System.out.print(" musta");
			}
			if (i == 15) {
				System.out.print(" VALKOINEN");
			}
			System.out.println("");
		}
		System.out.println("    A   B   C   D   E   F   G   H");
		/*System.out.println("\nMustat nappulat:");
		ArrayList<Piece> pieces = board.getPieces(false);
		for (Piece onePiece : pieces) {
			System.out.println(onePiece.getSign() + " r" + (onePiece.getPosition()/8) + " s" + (onePiece.getPosition()%8));
		}
		System.out.println("\nValkoiset nappulat:");
		ArrayList<Piece> wpieces = board.getPieces(true);
		for (Piece onePiece : wpieces) {
			System.out.println(onePiece.getSign() + " r" + (onePiece.getPosition()/8) + " s" + (onePiece.getPosition()%8));
		}*/
	}
	
}
