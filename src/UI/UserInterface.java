package UI;

import AI.AI;
import Chessboard.Chessboard;
import Chessboard.ChessboardHandler;
import Chessboard.Move;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Static class to draw the user interface.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class UserInterface {
	
	private static Chessboard chessboard;
	private static Scanner input;
	private static String userCommand;
	private static int gameTreeDepth;
	
	public static void runGame() {
		init();
		int gameTreeDepthLimit = 8;
		System.out.println("========================================================\n"
						 + "======================== SHAKKI ========================\n"
						 + "============== (c) 2015 Tuukka Paukkunen ===============\n"
						 + "====================== Versio 1.0 ======================\n"
						 + "========================================================\n");
		System.out.println("Anna pelipuun syvyys (1- " + gameTreeDepthLimit + ").\n"
				+ "Syvempi pelipuu tarkoittaa sekä parempaa tietokoneen\n"
				+ "suoriutumista pelistä että pidempiä viiveitä tietokoneen\n"
				+ "siirtovuorolla:");
		while (gameTreeDepth  < 1 || gameTreeDepth > gameTreeDepthLimit) {
			try {
 				String depthValue = input.nextLine();
				gameTreeDepth = Integer.parseInt(depthValue);
				if (gameTreeDepth < 1 || gameTreeDepth > gameTreeDepthLimit) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				if (gameTreeDepth > gameTreeDepthLimit) {
					System.out.println("En ole mikään Deep Blue. Anna luku (1- " + gameTreeDepthLimit + "):");
				} else {
					System.out.println("Syötit pelipuun syvyyden väärin. Anna luku (1- " + gameTreeDepthLimit + "):");
				}
			}
		}
		System.out.println("Haluatko, että tietokone pelaa itseään vastaan?\n"
				+ "Vastaa \"k\" tai \"e\".");
		String command = input.nextLine();
		if (command.equals("k")) {
			pcVsPC();
		} else {
			humanVsPC();
		}
	}
	
	/**
	 * Method initializes the game.
	 */
	private static void init() {
		chessboard = new Chessboard();
		input = new Scanner(System.in);
		gameTreeDepth = 0;
	}
	
	/**
	 * Method is used with computer vs computer game.
	 */
	private static void pcVsPC() {
		AI ai1 = new AI(true);
		AI ai2 = new AI(false);
		boolean ai1Ok = true, ai2Ok = true;
		while (ai2Ok && ai1Ok) {
			ai1Ok = computersTurn(ai1, false);
			if (ai1Ok) {
				ai2Ok = computersTurn(ai2, false);
			}
		}
	}
	
	/**
	 * Method to handle the human vs. computer game.
	 */
	private static void humanVsPC() {
		AI ai = new AI(false);
		System.out.println("\nPeli vastaanottaa siirtokomennot koordinaatteina.\n"
				+ "Esimerkiksi komento c2c4 siirtää koordinaatissa c2 olevan\n"
				+ "nappulan koordinaattiin c4. Peli tarkistaa, etteivät\n"
				+ "koordinaatit ole laudan ulkopuolella ja että siirtokomento\n"
				+ "on shakin sääntöjen mukainen.\n"
				+ "\n"
				+ "Tietokoneen viimeiseksi siirtämä nappula näkyy vihreänä.");
		boolean computersTurnOk = true;
		drawBoard(chessboard.getBoardAsCharArray(), null);
		while (computersTurnOk && usersTurn()) {
			computersTurnOk = computersTurn(ai, true);
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
			userCommand = input.nextLine();
			if (userCommand.equals("stop")) {
				System.out.println("Poistutaan pelistä.");
				return false;
			} else if (ChessboardHandler.userCommandParser(userCommand, chessboard)) {
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
	 * Calculates, how much time has passed between startTime and endTime.
	 * @param startTime Long, start time in milliseconds.
	 * @param endTime Long, end time in milliseconds.
	 * @return String, e.g. "3 min 25 s" or "5,433 s".
	 */
	private static String calculateMoveTime(long startTime, long endTime) {
		StringBuilder moveTime = new StringBuilder();
		long timeInMillis = endTime - startTime;
		int timeInSec = ((int)timeInMillis)/1000;
		
		if(timeInSec > 59) {
			moveTime.append(timeInSec/60);
			moveTime.append(" min ");
		}
		moveTime.append(timeInSec%60);
		if (timeInSec < 10) {
			moveTime.append(",");
			moveTime.append(timeInMillis%1000);
		}
		moveTime.append(" s");
		
		return moveTime.toString();
	}
	
	/**
	 * Method handles the UI on the computer's turn.
	 * @param ai AI-object, which determines the computer's next move.
	 * @param chessboard Chessboard-object, which game is played.
	 * @return True, if computer can continue the game, false otherwise.
	 */
	private static boolean computersTurn(AI ai, boolean humanVsPC) {
		String computersColour;
		System.out.println("\nOdota. Tietokone tekee siirtonsa...");
		long startTime = System.currentTimeMillis();
		Move move = null;
		try {
			move = ai.getMove(chessboard, gameTreeDepth);
		} catch (NullPointerException ne) {
			Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ne);
		} catch (Exception e) {
			Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, e);
		}
		// move from 0 to 0 means black lost the game
		if (move.getStart() == 0 && move.getEnd() == 0) {
			if (humanVsPC) {
				System.out.println("Sinä voitit!");
			} else {
				computersColour = ai.getColour() ? "mustilla" : "valkoisilla";
				System.out.println("Tietokone voitti " + computersColour + "!");
			}
			return false;
		}
		ChessboardHandler.makeMove(chessboard, move);
		long endTime = System.currentTimeMillis();
		drawBoard(chessboard.getBoardAsCharArray(), move);
		System.out.println("Siirrossa kului aikaa " + calculateMoveTime(startTime, endTime) + ".");
		int checkMateValue = ai.getColour() ? 0 : 1;
		if (ChessboardHandler.isItCheckMate(chessboard) == checkMateValue) {
			computersColour = ai.getColour() ? "valkoisilla" : "mustilla";
			System.out.println("Tietokone voitti " + computersColour + "!");
			return false;
		} else {
			return true;
		}

	}
	
	/**
	 * Method uses the char-array of this class as an input and outputs human
	 * readable ASCII-character version of the chess board.
	 * @param chessboard Two dimensional char-array, the board to be drawn.
	 * @param move Move-object's information is used to highlight computers last moved piece.
	 */
	public static void drawBoard(char[][] chessboard, Move move) {
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
									// Highlights computer's last moved piece.
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
	}
	
}
