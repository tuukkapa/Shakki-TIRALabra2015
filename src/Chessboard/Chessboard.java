package Chessboard;

import Chessboard.pieces.*;

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
	private Piece[] pieces; // pieces are only black at this point
	
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
		pieces = new Piece[16];
		for (int i = 0; i < pieces.length; i++) {
			pieces[i] = new Pawn(false, 8 + i);
		}
		// TODO create the rest of the pieces
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
	
	public Piece[] clonePieces() throws CloneNotSupportedException {
		Piece[] newPieces = new Piece[16];
		for (int i = 0; i < pieces.length; i++) {
			newPieces[i] = (Pawn) pieces[i].clone();
		}
		return newPieces;
	}
	
	public Piece[] getPieces() {
		return pieces;
	}
	
	public void setPieces(Piece[] newPieces) {
		this.pieces = newPieces;
	}
	
	/**
	 * Returns the chessboard as char-array.
	 * @return Returns the chessboard as char-array.
	 */
	public char[][] cloneBoard() {
		char[][] newBoard = new char[8][8];
		for (int i = 0; i < chessboard.length; i++) {
			System.arraycopy(chessboard[i], 0, newBoard[i], 0, chessboard[i].length);
		}
		return newBoard;
	}
	
	public char[][] getBoard() {
		return chessboard;
	}
	
	public void setBoard(char[][] newboard) {
		chessboard = newboard;
		this.updateKingPosition(true);
		this.updateKingPosition(false);
	}
	
	/**
	 * Moves pawn and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return boolean value, whether the move is successfully done.
	 */
	public boolean movePawn(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'P') {
			return false;
		}
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char pawn = colour ? 'P' : 'p';
		int homeRow = colour ? 6 : 1;
		char[][] tempBoard = chessboard;
		
		if (colour) {
			if (startRow <= endRow) {
				return false;
			}
		} else {
			if (startRow >= endRow) {
				return false;
			}
		}
		
		// move forward
		if (startCol == endCol) {
			if (((Math.abs(startRow - endRow) == 2 && startRow == homeRow) || Math.abs(startRow - endRow) == 1) && chessboard[endRow][endCol] == ' ') {
				chessboard[startRow][startCol] = ' ';
				chessboard[endRow][endCol] = pawn;
				if (!this.isItCheck(colour)) {
					return true;
				}
			}
		// capture
		} else {
			if ((Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) == 1) && this.endSquareContainsEnemy(colour, endRow, endCol)) {
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
	 * Moves rook and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveRook(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'R') {
			return false;
		}	
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char rook = colour ? 'R' : 'r';
		char[][] tempBoard = chessboard;

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
		
		if (this.endSquareContainsEnemyOrEmpty(colour, endRow, endCol)) {
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
	 * Moves knight and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveKnight(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'N') {
			return false;
		}	
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char knight = colour ? 'N' : 'n';
		char[][] tempBoard = chessboard;
		
		if ((Math.abs(startCol-endCol) == 1 && Math.abs(startRow-endRow) == 2) || 
				Math.abs(startCol-endCol) == 2 && Math.abs(startRow-endRow) == 1) {
			if (this.endSquareContainsEnemyOrEmpty(colour, endRow, endCol)) {
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
	 * Moves bishop and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveBishop(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'B') {
			return false;
		}	
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char bishop = colour ? 'B' : 'b';
		char[][] tempBoard = chessboard;
		
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
			if (this.endSquareContainsEnemyOrEmpty(colour, endRow, endCol)) {
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
	 * Moves queen and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveQueen(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'Q') {
			return false;
		}	
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char queen = colour ? 'Q' : 'q';
		char[][] tempBoard = chessboard;
		
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
		
		if (this.endSquareContainsEnemyOrEmpty(colour, endRow, endCol)) {
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
	 * Moves king and possibly captures an enemy piece, if allowed by chess rules.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if move is successfully done, false otherwise.
	 */
	public boolean moveKing(int startRow, int startCol, int endRow, int endCol) {
		if (!this.isCommandValid(startRow, startCol, endRow, endCol)) {
			return false;
		}
		
		if (Character.toUpperCase(chessboard[startRow][startCol]) != 'K') {
			return false;
		}
		boolean colour = Character.isUpperCase(chessboard[startRow][startCol]);
		char king = colour ? 'K' : 'k';
		char[][] tempBoard = chessboard;
		
		if (Math.abs(endRow-startRow) <= 1 && Math.abs(endCol-startCol) <= 1 && this.endSquareContainsEnemyOrEmpty(colour, endRow, endCol)) {
			chessboard[startRow][startCol] = ' ';
			chessboard[endRow][endCol] = king;
			updateKingPosition(colour);
			if (!isItCheck(colour)) {
				return true;
			}
		}
		chessboard = tempBoard;
		updateKingPosition(colour);
		return false;
	}
	
	/**
	 * Determines if end square contains enemy or is empty.
	 * @param colour True, if piece to be moved is white, false otherwise.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if end square contains enemy or is empty, false otherwise.
	 */
	private boolean endSquareContainsEnemyOrEmpty(boolean colour, int endRow, int endCol) {
		boolean onTargetSquareIsEnemyPiece = colour ? Character.isLowerCase(chessboard[endRow][endCol]) : Character.isUpperCase(chessboard[endRow][endCol]);	
		return chessboard[endRow][endCol] == ' ' || onTargetSquareIsEnemyPiece;
	}
	
	
	 /** Determines if end square contains enemy.
	 * @param colour True, if piece to be moved is white, false otherwise.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True, if square contains enemy, false otherwise.
	 */
	public boolean endSquareContainsEnemy(boolean colour, int endRow, int endCol) {
		return colour ? Character.isLowerCase(chessboard[endRow][endCol]) : Character.isUpperCase(chessboard[endRow][endCol]);	
	}

	/**
	 * Method updates king's position at the char array.
	 * @param colour True, if piece to be moved is white, false otherwise.
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
		char pawn, rook, knight, bishop, queen, king;
		int kRow, kCol;
		
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
		
		// is piece past enemy's king, e.g. pawn
		boolean pieceIsPastKing;
		
		for (int i = 0; i < 64; i++) {
			// is Pawn threatening
			if (Math.abs(kRow - (i/8)) == 1 && Math.abs(kCol - (i%8)) == 1) {
				pieceIsPastKing = checkedIsWhite ? kRow < i/8 : kRow > i/8;
				if (chessboard[i/8][i%8] == pawn && !pieceIsPastKing) {
					return true;
				}
			}
			
			// is Knight threatening
			if ((Math.abs(kRow - (i/8)) == 2 && Math.abs(kCol - (i%8)) == 1) || 
					(Math.abs(kRow - (i/8)) == 1 && Math.abs(kCol - (i%8)) == 2)) {
				if (chessboard[i/8][i%8] == knight) {
					return true;
				}
			}
			
			// is Bishop threatening or Queen threatening diagonally
			if (Math.abs(kRow - (i/8)) == Math.abs(kCol - (i%8)) && (chessboard[i/8][i%8] == bishop || chessboard[i/8][i%8] == queen)) {
				boolean routeFree = true;
				for (int j = 1; j < Math.abs(kRow -(i/8)); j++) {
					/*	Index out of bounds doesn't happen because when either of the two first expressions are false
						then the third one isn't checked. In other words, the first two prevent checking outside of
						the array.
					*/
					/*	Check north-west:
						current row is less than king's row
						current column is less than stking's column
					*/
					if (i/8 < kRow && i%8 < kCol && chessboard[kRow-j][kCol-j] != ' ') {
						routeFree = false;
					}
					// Check south-west
					if (i/8 > kRow && i%8 < kCol && chessboard[kRow+j][kCol-j] != ' ') {
						routeFree = false;
					}
					// Check north-east
					if (i/8 < kRow && i%8 > kCol && chessboard[kRow-j][kCol+j] != ' ') {
						routeFree = false;
					}
					// Check south-east
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
	
	public boolean isItCheckMate() {
		// TODO
		return true;
	}
	
	/**
	 * Returns the value of the game situation at the chessboard.
	 * @param True if computer plays black pieces, false otherwise.
	 * @return Integer, higher means better chances at winning.
	 */
	public int getValue(boolean computerIsBlack) {
		// TODO
		return 1;
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
	
	public void setSquare(int position, char contents) {
		chessboard[position/8][position%8] = contents;
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
	
	/**
	 * Does the basic validation of given movement, i.e. is any of the coordinates
	 * outside of the board.
	 * @param startRow Row where the piece to be moved is.
	 * @param startCol Column where the piece to be moved is.
	 * @param endRow Row where the piece is to be moved.
	 * @param endCol Column where the piece is to be moved.
	 * @return True if coordinates are valid, false otherwise.
	 */
	private boolean isCommandValid(int startRow, int startCol, int endRow, int endCol) {
		return !(startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 || endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7);
	}
	
}
