
import java.util.Arrays;

/**
 * This class does everything related to the chess board and it's pieces' movement.
 * @author Tuukka Paukkunen
 */
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
	
	private char[][] chessboard;
	private int whiteKingPosition, blackKingPosition;
	
	public Chessboard() {
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		chessboard = newboard;
		whiteKingPosition = 60;
		blackKingPosition= 4;
	}
	
	/**
	 * Method creates a chessboard usable for testing.
	 * @param check True, if the game situation is check, false otherwise.
	 */
	public void makeTestBoard(boolean check) {
		if (check) {
			char[][] newboard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'r', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
				{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
			};
			chessboard = newboard;
			updateKingPosition(true);
			updateKingPosition(false);
			return;
		}
		char[][] newboard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', 'B', ' ', 'N', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'Q'},
			{'Q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', 'R'},
			{' ', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		chessboard = newboard;
		updateKingPosition(true);
		updateKingPosition(false);
	}
	
	/**
	 * Returns the chessboard as char-array.
	 * @return Returns the chessboard as char-array.
	 */
	public char[][] getBoard() {
		return chessboard;
	}
	
	public boolean areCoordinatesOutsideBoard(int startRow, int startCol, int endRow, int endCol) {
		return startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 || endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7;
	}
	
	/**
	 * Moves white pawn and possibly captures a black piece, if allowed by chess rules.
	 * @param colour True if white piece is moved, false otherwise.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return boolean value, whether the move is successfully done.
	 */
	public boolean movePawn(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char pawn = colour ? 'P' : 'p';
		int homeRow = colour ? 6 : 1;
		char[][] tempBoard = chessboard;
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		// move forward
		if (startCol == endCol) {
			if (((Math.abs(startRow - endRow) == 2 && startRow == homeRow) || Math.abs(startRow - endRow) == 1) && this.canMoveToTargetSquare(colour, endRow, endCol)) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = pawn;
				if (!this.isItCheck(colour)) {
					return true;
				}
			}
		// capture
		} else {
			if ((Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) && this.canMoveToTargetSquare(colour, endRow, endCol)) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = pawn;
				if (!this.isItCheck(colour)) {
					return true;
				}
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Moves white rook and possibly captures a black piece, if allowed by chess rules.
	 * @param colour True, if the piece to be moved is white, false otherwise.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveRook(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char rook = colour ? 'R' : 'r';
		char[][] tempBoard = chessboard;
		
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}

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
		
		if (this.canMoveToTargetSquare(colour, endRow, endCol)) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = rook;
			if (!this.isItCheck(colour)) {
				return true;
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Moves white knight and possibly captures a black piece, if allowed by chess rules.
	 * @param colour True, if the piece to be moved is white, false otherwise.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveKnight(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char knight = colour ? 'N' : 'n';
		char[][] tempBoard = chessboard;
		
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if ((Math.abs(startCol-endCol) == 1 && Math.abs(startRow-endRow) == 2) || 
				Math.abs(startCol-endCol) == 2 && Math.abs(startRow-endRow) == 1) {
			if (this.canMoveToTargetSquare(colour, endRow, endCol)) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = knight;
				if (!this.isItCheck(colour)) {
					return true;
				}
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Moves white bishop and possibly captures a black piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveBishop(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char bishop = colour ? 'B' : 'b';
		char[][] tempBoard = chessboard;
		
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			for (int i = 1; i < Math.abs(endRow-startRow); i++) {
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
			if (this.canMoveToTargetSquare(colour, endRow, endCol)) {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = bishop;
				if (!this.isItCheck(colour)) {
					return true;
				}
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Moves white queen and possibly captures a black piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveQueen(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char queen = colour ? 'Q' : 'q';
		char[][] tempBoard = chessboard;
		
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		// Check route diagonally
		if (Math.abs(startCol-endCol) == Math.abs(startRow-endRow)) {
			for (int i = 1; i < Math.abs(endRow-startRow); i++) {
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
			for (int i = 1; i < Math.abs(endRow-startRow); i++) {
				if (chessboard[startRow+i][startCol] != ' ') {
					return false;
				}
			}	
		
		// Check route horizontally
		} else if (startRow == endRow) {
			for (int i = 1; i < Math.abs(endCol-startCol); i++) {
				if (chessboard[startRow][startCol+i] != ' ') {
					return false;
				}
			}
		}
		
		if (this.canMoveToTargetSquare(colour, endRow, endCol)) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = queen;
			if (!this.isItCheck(colour)) {
				return true;
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Moves white king and possibly captures a black piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveKing(boolean colour, int startRow, int startCol, int endRow, int endCol) {
		char king = colour ? 'K' : 'k';
		char[][] tempBoard = chessboard;
		
		if (this.areCoordinatesOutsideBoard(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && this.canMoveToTargetSquare(colour, endRow, endCol)) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = king;
			if (!isItCheck(colour)) {
				updateKingPosition(colour);
				return true;
			}
		}
		chessboard = tempBoard;
		return false;
	}
	
	/**
	 * Determines if chess piece can be moved into the given coordinates by chess rules.
	 * @param colour True, if piece to be moved is white, false otherwise.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if piece can be moved to the coordinates, false otherwise.
	 */
	public boolean canMoveToTargetSquare(boolean colour, int endRow, int endCol) {
		boolean onTargetSquareIsEnemyPiece = colour ? Character.isLowerCase(chessboard[endRow][endCol]) : Character.isUpperCase(chessboard[endRow][endCol]);	
		return chessboard[endRow][endCol] == ' ' || onTargetSquareIsEnemyPiece;
	}

	/*
	 * Method updates king's position at the char array.
	 * @param white True, if piece to be moved is white, false otherwise.
	 */
	public void updateKingPosition(boolean colour) {
		char king = colour ? 'K' : 'k';
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] == king) {
				if (colour) {
					whiteKingPosition = i;
				} else {
					blackKingPosition = i;
				}
				break;
			}
		}
	}
	
	/**
	 * Determines, if the game situation is check. This method
	 * can be used with either side of the player by using boolean parameter
	 * checkedIsWhite.
	 * @param checkedIsWhite True, if the checked player (the player who's King is threatened) is white, false otherwise.
	 * @return True if game situation is check, false otherwise.
	 */
	public boolean isItCheck(boolean checkedIsWhite) {
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
				for (int j = 1; j < Math.abs(kRow -(i/8)); j++) {
					if (i/8 < kRow && i%8 < kCol && chessboard[kRow-j][kCol-j] != ' ') {
						routeFree = false;
					}
					if (i/8 > kRow && i%8 < kCol && chessboard[kRow+j][kCol-j] != ' ') {
						routeFree = false;
					}
					if (i/8 < kRow && i%8 > kCol && chessboard[kRow-j][kCol+j] != ' ') {
						routeFree = false;
					}
					if (i/8 > kRow && i%8 > kCol && chessboard[kRow+j][kCol+j] != ' ') {
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
				for (int j = 1; j < Math.abs(kCol - (i%8)); j++) {
					if (kCol < i%8) {
						if (chessboard[kRow][kCol+j] != ' ') {
							routeFree = false;
						}
					} else {
						if (chessboard[kRow][kCol-j] != ' ') {
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
				for (int j = 1; j < Math.abs(kRow - (i/8)); j++) {
					if (kRow < i/8) {
						if (chessboard[kRow+j][kCol] != ' ') {
							routeFree = false;
						}
					} else {
						if (chessboard[kRow-j][kCol] != ' ') {
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
	
	/**
	 * Returns contents of one chess board square.
	 * @param row Row of the square.
	 * @param col Column of the square.
	 * @return Character, contents of the square.
	 */
	public char getSquareContents(int row, int col) {
		return chessboard[row][col];
	}
	
	/**
	 * Moves king's position for testing purposes.
	 * @param row Row of the new position.
	 * @param col Column of the new position.
	 * @param colour Colour of the king, true = white, false = black.
	 */
	public void moveKingForTest(boolean colour, int row, int col) {
		if (colour) {
			chessboard[row][col] = 'K';
		} else {
			chessboard[row][col] = 'k';
		}
	}
	
}
