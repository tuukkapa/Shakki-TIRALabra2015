
import java.util.Arrays;

public class Chessboard {
	/*
		Rook (R, r) = torni
		Knight (N, n) = hevonen
		Bishop (B, b) = lähetti
		Queen (Q, q) = kuningatar
		King (K, k) = kuningas
		Pawn (P, p) = sotilas
	
		Iso kirjain = valkoinen
		Pieni kirjain = musta
	
		Esim.
	
		R = valkoinen torni
		n = musta hevonen
	*/	
	
	private static long r = 0L, n = 0L, b = 0L, q = 0L, k = 0L, p = 0L;
	private static long R = 0L, N = 0L, B = 0L, Q = 0L, K = 0L, P = 0L;
	private static char[][] chessboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
	
	public static void draw() {
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
								// (i-1)/2 + 1 calculates board's rownumber from i
								System.out.print((i-1)/2 + 1 + " |");
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
								System.out.print(chessboard[(i-1)/2][(j-2)/4]);
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
	
	public static boolean movePiece(String command) {
		if (command.length() != 4) {
			return false;
		}
		int startCol, startRow, endCol, endRow;
		startCol = (int)command.charAt(0) - 65;
		startRow = 8 - Integer.parseInt(command.substring(1, 2));
		endCol = (int)command.charAt(2) - 65;
		endRow = 8 - Integer.parseInt(command.substring(3));
		boolean error = startCol < 0 || startCol > 7 ||
				startRow < 0 || startRow > 7 ||
				endCol < 0 || endCol > 7 ||
				endRow < 0 || endRow > 7;
		if (error) {
			return error;
		}
		switch(chessboard[startRow][startCol]) {
			case 'P': return movePawn(startRow, startCol, endRow, endCol);
			case 'R': return moveRook(startRow, startCol, endRow, endCol);
			case 'N': return moveKnight(startRow, startCol, endRow, endCol);
			case 'B': return moveBishop(startRow, startCol, endRow, endCol);
			case 'Q': return moveQueen(startRow, startCol, endRow, endCol);
			case 'K': return moveKing(startRow, startCol, endRow, endCol);
			default: return false;
		}
	}
	
	/**
	 * Moves white pawn and possibly captures a black piece, if allowed by chess rules.
	 * 
	 * @param startRow Row where the piece to be moved is
	 * @param startCol Column where the piece to be moved is
	 * @param endRow Row where the piece is to be moved
	 * @param endCol Column where the piece is to be moved
	 * @return boolean value, whether the move is successfully done
	 */
	public static boolean movePawn(int startRow, int startCol, int endRow, int endCol) {
		// Move forward
		if (startCol == endCol) {
			if (((startRow == 6 && endRow >= 4 && chessboard[endRow+1][endCol] == ' ') || startRow - endRow == 1) && chessboard[endRow][endCol] == ' ') {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = 'P';
				return true;
			} else {
				return false;
			}
		// Capture
		} else if (Math.abs(startCol - endCol) == 1 && 
				startRow - endRow == 1 && 
				Character.isLowerCase(chessboard[endRow][endCol])) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = 'P';
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Moves white rook and possibly captures a black piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is
	 * @param startCol Column where the piece to be moved is
	 * @param endRow Row where the piece is to be moved
	 * @param endCol Column where the piece is to be moved
	 * @return boolean value, whether the move is successfully done
	 */
	public static boolean moveRook(int startRow, int startCol, int endRow, int endCol) {
		// Check route vertically
		if (startCol == endCol) {
			for (int i = startRow; i < endRow; i++) {
				if (chessboard[i][startCol] != ' ') {
					return false;
				}
			}	
		// Check route horizontally
		} else if (startRow == endRow) {
			for (int i = startCol; i < endCol; i++) {
				if (chessboard[startRow][i] != ' ') {
					return false;
				}
			}
		} else if (startRow != endRow && startCol != endCol) {
			return false;
		}
		
		if (chessboard[endRow][endCol] == ' ' || Character.isLowerCase(chessboard[endRow][endCol])) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = 'R';
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean moveKnight(int startRow, int startCol, int endRow, int endCol) {
		if ((Math.abs(startCol-endCol) == 1 && Math.abs(startRow-endRow) == 2) || 
				Math.abs(startCol-endCol) == 2 && Math.abs(startRow-endRow) == 1) {
			if (chessboard[endRow][endCol] == ' ' || Character.isLowerCase(chessboard[endRow][endCol])) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = 'N';
				return true;
			}
		}
		return false;
	}
	
	public static boolean moveBishop(int startRow, int startCol, int endRow, int endCol) {
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			for (int i = 0; i < Math.abs(endRow-startRow); i++) {
				if (endRow>startRow && endCol>startCol) {
					if (chessboard[startRow+i][startCol+i] != ' ') {
						return false;
					}
				} else if (endRow>startRow && endCol<startCol) {
					if (chessboard[startRow+i][startCol-i] != ' ') {
						return false;
					}
				} else if (endRow<startRow && endCol>startCol) {
					if (chessboard[startRow-i][startCol+i] != ' ') {
						return false;
					}
				} else {
					if (chessboard[startRow-i][endRow-i] != ' ') {
						return false;
					}
				}
			}
			if (chessboard[endRow][endCol] == ' ' || Character.isLowerCase(chessboard[endRow][endCol])) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = 'B';
				return true;
			}
		}
		return false;
	}
	
	public static boolean moveQueen(int startRow, int startCol, int endRow, int endCol) {
		// Check route diagonally
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			for (int i = 0; i < Math.abs(endRow-startRow); i++) {
				if (endRow>startRow && endCol>startCol) {
					if (chessboard[startRow+i][startCol+i] != ' ') {
						return false;
					}
				} else if (endRow>startRow && endCol<startCol) {
					if (chessboard[startRow+i][startCol-i] != ' ') {
						return false;
					}
				} else if (endRow<startRow && endCol>startCol) {
					if (chessboard[startRow-i][startCol+i] != ' ') {
						return false;
					}
				} else {
					if (chessboard[startRow-i][endRow-i] != ' ') {
						return false;
					}
				}
			}
		// Check route vertically
		} else if (startCol == endCol) {
			for (int i = startRow; i < endRow; i++) {
				if (chessboard[i][startCol] != ' ') {
					return false;
				}
			}	
		
		// Check route horizontally
		} else if (startRow == endRow) {
			for (int i = startCol; i < endCol; i++) {
				if (chessboard[startRow][i] != ' ') {
					return false;
				}
			}
		}
		
		if (chessboard[endRow][endCol] == ' ' || Character.isLowerCase(chessboard[endRow][endCol])) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = 'Q';
			return true;
		}	
		return false;
	}
	
	public static boolean moveKing(int startRow, int startCol, int endRow, int endCol) {
		return true;
	}
	
	public static void main(String[] args) {
		draw();
	}
	
}
