
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
	private static int whiteKingPosition, blackKingPosition;
	
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
		boolean movedone;
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
		char[][] tempboard = chessboard;
		switch(chessboard[startRow][startCol]) {
			case 'P': movedone = movePawn(startRow, startCol, endRow, endCol);
			case 'R': movedone = moveRook(startRow, startCol, endRow, endCol);
			case 'N': movedone = moveKnight(startRow, startCol, endRow, endCol);
			case 'B': movedone = moveBishop(startRow, startCol, endRow, endCol);
			case 'Q': movedone = moveQueen(startRow, startCol, endRow, endCol);
			case 'K': movedone = moveKing(startRow, startCol, endRow, endCol);
			default: movedone = false;
		}
		if (!movedone) {
			return false;
		}
		if (isItCheck(true)) {
			chessboard = tempboard;
			return false;
		}
		return true;
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
		char tempChar;
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && canMoveToTargetSquare(endRow, endCol)) {
			chessboard[startRow][startCol] = ' ';
			tempChar = chessboard[endRow][endCol];
			chessboard[endRow][endCol] = 'K';
			if (!isItCheck(true)) {
				return true;
			} else {
				chessboard[startRow][startCol] = 'K';
				chessboard[endRow][endCol] = tempChar;
				return false;
			}
		}
		return false;
	}
	
	public static boolean canMoveToTargetSquare(int endRow, int endCol) {
		return chessboard[endRow][endCol] == ' ' || Character.isLowerCase(chessboard[endRow][endCol]);
	}

	public static void updateBlackKingPosition() {
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] == 'k') {
				blackKingPosition = i;
				break;
			}
		}
	}
	
	public static void updateWhiteKingPosition() {
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] == 'K') {
				whiteKingPosition = i;
				break;
			}
		}
	}
	
	/**
	 * Returns boolean value, if the game situation is chess. This method
	 * can be used with either side of the player by using boolean parameter
	 * checkedIsWhite.
	 * @param checkedIsWhite True, if the checked player is white, false otherwise
	 * @return True if game situation is check, false otherwise
	 */
	public static boolean isItCheck(boolean checkedIsWhite) {
		char pawn;
		char rook;
		char knight;
		char bishop;
		char queen;
		char king;
		int kRow;
		int kCol;
		
		if (checkedIsWhite) {
			pawn = 'p';
			rook = 'r';
			knight = 'n';
			bishop = 'b';
			queen = 'q';
			king = 'k';
			kRow = whiteKingPosition/8;
			kCol = whiteKingPosition%8;
			
		} else {
			pawn = 'P';
			rook = 'R';
			knight = 'N';
			bishop = 'B';
			queen = 'Q';
			king = 'K';
			kRow = blackKingPosition/8;
			kCol = blackKingPosition%8;
		}
		
		
		for (int i = 0; i < 64; i++) {
			// is Pawn threatening
			// TODO change this to use Math.abs(kRow-(i/8) == 1 etc to be usable for both colours
			if ((i/8 == kRow -1 && i%8 == kCol - 1) || (i/8 == kRow - 1 && i%8 == kCol +1)) {
				if (chessboard[i/8][i%8] == pawn) {
					return true;
				}
			}
			
			// is Knight threatening
			if ((Math.abs(kRow-(i/8)) == 2 && Math.abs(kCol-(i%8)) == 1) || 
					(Math.abs(kRow-(i/8)) == 1 && Math.abs(kCol-(i%8)) == 2)) {
				if (chessboard[i/8][i%8] == knight) {
					return true;
				}
			}
			
			// is Bishop threatening or Queen threatening diagonally
			if (Math.abs(kRow -(i/8)) == Math.abs(kCol -(i%8)) && (chessboard[i/8][i%8] == bishop || chessboard[i/8][i%8] == queen)) {
				boolean routeFree = true;
				for (int j = 0; j < Math.abs(kRow -(i/8)); j++) {
					if (i/8 < kRow && i%8 < kCol && chessboard[kRow-i][kCol-i] != ' ') {
						routeFree = false;
					}
					if (i/8 > kRow && i%8 < kCol && chessboard[kRow+i][kCol-i] != ' ') {
						routeFree = false;
					}
					if (i/8 < kRow && i%8 > kCol && chessboard[kRow-i][kCol+i] != ' ') {
						routeFree = false;
					}
					if (i/8 > kRow && i%8 > kCol && chessboard[kRow+i][kCol+i] != ' ') {
						routeFree = false;
					}
				}
				if (routeFree) {
					return true;
				}
			}
			
			
			// is Rook or Queen threatening horizontally
			if (kRow == i/8 && (chessboard[i/8][i%8] == rook || chessboard[i/8][i%8] == queen)) {
				boolean routeFree = true;
				for (int j = 0; j < Math.abs(kCol - (i%8)); j++) {
					if (kCol < i%8) {
						if (chessboard[kRow][kCol+i] != ' ') {
							routeFree = false;
						}
					} else {
						if (chessboard[kRow][kCol-i] != ' ') {
							routeFree = false;
						}
					}	
				}
				if (routeFree) {
					return true;
				}
			}
			
			// is Rook or Queen threatening vertically
			if (kCol == i%8 && (chessboard[i/8][i%8] == rook || chessboard[i/8][i%8] == queen)) {
				boolean routeFree = true;
				for (int j = 0; j < Math.abs(kRow - (i/8)); j++) {
					if (kRow < i/8) {
						if (chessboard[kRow+i][kCol] != ' ') {
							routeFree = false;
						}
					} else {
						if (chessboard[kRow-i][kCol] != ' ') {
							routeFree = false;
						}
					}
				}
				if (routeFree) {
					return true;
				}
			}
			
			// is opponent's King threatening
			if (chessboard[i/8][i%8] == king && (Math.abs((i/8) - kRow) <= 1 && Math.abs((i%8) - kCol) <= 1)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		draw();
	}
	
}
