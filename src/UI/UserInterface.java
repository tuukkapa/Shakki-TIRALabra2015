package UI;

import Chessboard.Chessboard;
import Chessboard.Move;
import Chessboard.pieces.Piece;
import java.util.ArrayList;

/**
 * Static class to draw the user interface.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class UserInterface {
	
	/**
	 * Method uses the char-array of this class as an input and outputs human
	 * readable ASCII-character version of the chess board.
	 * @param chessboard Two dimensional char-array, the board to be drawn.
	 */
	public static void draw(char[][] chessboard, Move move/*, Chessboard board*/) {
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
