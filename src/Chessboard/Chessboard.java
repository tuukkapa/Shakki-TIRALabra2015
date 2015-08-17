package Chessboard;

import AI.Move;
import Chessboard.pieces.*;
import UI.UserInterface;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class does everything related to the chess board and it's pieces' movement.
 * It contains both the board and the piece-objects.
 * 
 * Fiels:
 * - Two-dimensional char-array, the chessboard.
 * - White and black King's positions as integers
 *   (0 = top left corner, 63 = bottom right corner).
 * - Two TreeMaps consisting of the pieces.
 *   Piece's position is as key and the Piece-object is as a value.
 * 
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
	private TreeMap<Integer, Piece> whitePieces, blackPieces;
	
	/**
	 * Constructor. Creates the chessboard, updates King's positions
	 * and calls createPieces-method, which creates the Piece-objects.
	 */
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
		this.createPieces();
	}
	
	/**
	 * Creates two TreeMaps, one for black and one for white. Creates piece-objects
	 * and puts those into the corresponding TreeMaps.
	 */
	private void createPieces() {
		whitePieces = new TreeMap<>();
		blackPieces = new TreeMap<>();
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] == 'p') blackPieces.put(i, new Pawn(false, i));
			if (chessboard[i/8][i%8] == 'r') blackPieces.put(i, new Rook(false, i));
			if (chessboard[i/8][i%8] == 'n') blackPieces.put(i, new Knight(false, i));
			if (chessboard[i/8][i%8] == 'b') blackPieces.put(i, new Bishop(false ,i));
			if (chessboard[i/8][i%8] == 'q') blackPieces.put(i, new Queen(false ,i));
			if (chessboard[i/8][i%8] == 'k') blackPieces.put(i, new King(false, i));
			if (chessboard[i/8][i%8] == 'P') whitePieces.put(i, new Pawn(true, i));
			if (chessboard[i/8][i%8] == 'R') whitePieces.put(i, new Rook(true, i));
			if (chessboard[i/8][i%8] == 'N') whitePieces.put(i, new Knight(true, i));
			if (chessboard[i/8][i%8] == 'B') whitePieces.put(i, new Bishop(true, i));
			if (chessboard[i/8][i%8] == 'Q') whitePieces.put(i, new Queen(true, i));
			if (chessboard[i/8][i%8] == 'K') whitePieces.put(i, new King(true, i));
		}
	}
	
	/**
	 * Clones and returns the pieces of the colour given as a parameter.
	 * @param white Colour of the pieces, true means white, false means black.
	 * @return TreeMap consisting of the piece-objects.
	 * @throws CloneNotSupportedException 
	 */
	public TreeMap clonePieces(boolean white) throws CloneNotSupportedException {
		TreeMap<Integer, Piece> newPieces = new TreeMap<>();
		if (white) {
			for (Map.Entry<Integer, Piece> piece : whitePieces.entrySet()) {
				newPieces.put(piece.getKey(), (Piece)piece.getValue().clone());
			}
		} else {
			for (Map.Entry<Integer, Piece> piece : blackPieces.entrySet()) {
				newPieces.put(piece.getKey(), (Piece)piece.getValue().clone());
			}
		}
		return newPieces;
	}
	
	/**
	 * Returns the pieces of the colour given as a parameter.
	 * @param white Colour of the pieces, true means white, false means black.
	 * @return TreeMap consisting of the piece-objects.
	 */
	public TreeMap getPieces(boolean white) {
		return white ? whitePieces : blackPieces;
	}
	
	/**
	 * Replaces the pieces with new ones of the colour given as a parameter.
	 * @param white Colour of the pieces, true means white, false means black.
	 * @param newPieces TreeMap consisting of the piece-objects.
	 */
	public void setPieces(boolean white, TreeMap newPieces) {
		if (white) {
			this.whitePieces = newPieces;
		} else {
			this.blackPieces = newPieces;
		}
	}
	
	/**
	 * Returns one piece on the position.
	 * @param position Integer from 0 to 63. 0 is top left corner, 63 is bottom right corner.
	 * @return The correct piece-object. Returns null if no piece is found.
	 */
	public Piece getPiece(int position) {
		Piece piece = whitePieces.get(position);
		if (piece == null) {
			return blackPieces.get(position);
		} else {
			return piece;
		}
	}
	
	/**
	 * Moves one piece from start position to end position.
	 * @param start Original position of the piece.
	 * @param end The target position of the piece to be moved.
	 * @return True if moving is successful, false otherwise.
	 */
	public boolean movePiece(int start, int end) {
		boolean success = false;
		Piece piece = null;
		if (whitePieces.containsKey(start)) {
			piece = whitePieces.get(start);
		} else {
			piece = blackPieces.get(start);
		}
		if (piece == null) {
			return false;
		}
		if (piece.isMoveValid(this, end)) {
			if (piece.endSquareContainsEnemy(this, end)) {
				this.getPieces(!piece.amIWhite()).remove(end);
			}
			this.setSquare(start, ' ');
			this.setSquare(end, piece.getSign());
			if (piece.amIWhite()) {
				whitePieces.put(end, whitePieces.get(start));
				whitePieces.remove(start);
				success = true;
			} else {
				blackPieces.put(end, blackPieces.get(start));
				blackPieces.remove(start);
				success = true;
			}
			if (success) {
				piece.setPosition(end);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Moves one piece from start position to end position.
	 * @param move Move-object, contains start and end coordinates.
	 * @return True if moving is successful, false otherwise.
	 */
	public boolean makeMove(Move move) {
		boolean success = false;
		int start = move.getStart();
		int end = move.getEnd();
		Piece piece = null;
		if (whitePieces.containsKey(start)) {
			piece = whitePieces.get(start);
		} else {
			piece = blackPieces.get(start);
		}
		if (piece == null) {
			return false;
		}
		if (piece.isMoveValid(this, end)) {
			if (piece.endSquareContainsEnemy(this, end)) {
				// save captured piece into Move for undoing
				move.setCapturedPiece((Piece)this.getPieces(!piece.amIWhite()).get(end));
				this.getPieces(!piece.amIWhite()).remove(end);
			}
			this.setSquare(start, ' ');
			this.setSquare(end, piece.getSign());
			if (piece.amIWhite()) {
				whitePieces.put(end, whitePieces.get(start));
				whitePieces.remove(start);
				success = true;
			} else {
				blackPieces.put(end, blackPieces.get(start));
				blackPieces.remove(start);
				success = true;
			}
			if (success) {
				piece.setPosition(end);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Undoes one move, i.e. gets the board back to it's original state before the move.
	 * @param move Move-object, contains start and end coordinates.
	 */
	public void undoMove(Chessboard chessboard, Move move) {
		Piece movedPiece = this.getPiece(move.getEnd());
		Piece capturedPiece = move.getCapturedPiece();
		this.setSquare(move.getEnd(), ' ');
		movedPiece.setPosition(move.getStart());
		this.setSquare(move.getStart(), movedPiece.getSign());
		if (capturedPiece != null) {
			this.setSquare(move.getEnd(), capturedPiece.getSign());
			capturedPiece.setPosition(move.getEnd());
			if (capturedPiece.amIWhite()) {
				whitePieces.put(move.getEnd(), capturedPiece);
			} else {
				blackPieces.put(move.getEnd(), capturedPiece);
			}
		}
	}
	
	/**
	 * Returns a clone of the chessboard as char-array.
	 * @return Chessboard as two dimensional char-array.
	 */
	public char[][] cloneBoard() {
		char[][] newBoard = new char[8][8];
		for (int i = 0; i < chessboard.length; i++) {
			System.arraycopy(chessboard[i], 0, newBoard[i], 0, chessboard[i].length);
		}
		return newBoard;
	}
	
	/**
	 * Returns the chessboard as char-array.
	 * @return Chessboard as two dimensional char-array.
	 */
	public char[][] getBoard() {
		return chessboard;
	}
	
	/**
	 * Replaces the board and pieces with a new one given as a parameter.
	 * @param newboard Two dimensional char-array of the board.
	 */
	public void setBoard(char[][] newboard) {
		chessboard = newboard;
		this.createPieces();
		this.updateKingPosition(true);
		this.updateKingPosition(false);
	}

	/**
	 * Method updates king's position at the char array.
	 * @param colour True, if piece to be moved is white, false otherwise.
	 */
	private void updateKingPosition(boolean colour) {
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
	 * Verifies, if the game situation is check. This method
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
	
	/**
	 * Verifies if the game situation would be check after a piece is moved.
	 * @param piece Piece-object to be moved.
	 * @param end Position, where the piece is to be moved.
	 * @return True, if the situation would be check, false otherwise.
	 */
	public boolean wouldItBeCheck(Piece piece, int end) {
		char endSquareBackup = this.getSquareContents(end);
		this.setSquare(piece.getPosition(), ' ');
		this.setSquare(end, piece.getSign());
		this.updateKingPosition(piece.amIWhite());
		boolean checkSituation = this.isItCheck(piece.amIWhite());
		this.setSquare(piece.getPosition(), piece.getSign());
		this.setSquare(end, endSquareBackup);
		this.updateKingPosition(piece.amIWhite());
		return checkSituation;
	}
	
	/**
	 * Verifies, if the game situation is checkmate.
	 * @param checkedIsWhite Colour, from which point of view the situation is viewed. True = white, false = black.
	 * @return True, if game situation is check mate, false otherwise.
	 */
	public boolean isItCheckMate(boolean checkedIsWhite) {
		Piece king = this.getPiece(checkedIsWhite ? whiteKingPosition : blackKingPosition);
		if (king == null) {
			return false;
		}
		ArrayList<Move> moves;
		moves = king.getPossibleMoves(this);
		return this.isItCheck(checkedIsWhite) && moves.isEmpty();
	}
	
	/**
	 * Returns the value of the game situation at the chessboard.
	 * @return Integer, higher means better chances at winning for the computer.
	 */
	public int getValue() {
		int points = 0;
		int kingPoints = 200;
		int queenPoints = 9;
		int rookPoints = 5;
		int knightPoints = 3;
		int bishopPoints = 3;
		int pawnPoints = 1;
		for (int i = 0; i < 64; i++) {
			if (chessboard[i/8][i%8] == 'k') points += kingPoints;
			if (chessboard[i/8][i%8] == 'q') points += queenPoints;
			if (chessboard[i/8][i%8] == 'r') points += rookPoints;
			if (chessboard[i/8][i%8] == 'n') points += knightPoints;
			if (chessboard[i/8][i%8] == 'b') points += bishopPoints;
			if (chessboard[i/8][i%8] == 'p') points += pawnPoints;
			if (chessboard[i/8][i%8] == 'K') points -= kingPoints;
			if (chessboard[i/8][i%8] == 'Q') points -= queenPoints;
			if (chessboard[i/8][i%8] == 'R') points -= rookPoints;
			if (chessboard[i/8][i%8] == 'N') points -= knightPoints;
			if (chessboard[i/8][i%8] == 'B') points -= bishopPoints;
			if (chessboard[i/8][i%8] == 'P') points -= pawnPoints;
		}
		if (this.isItCheck(true)) {
			points += 1000;
			if (this.isItCheckMate(true)) {
				points = Integer.MAX_VALUE;
			}
		}
		if (this.isItCheck(false)) {
			points -= 1000;
			if (this.isItCheckMate(false)) {
				points = Integer.MIN_VALUE;
			}
		}
		return points;
	}
	
	/**
	 * Returns contents of one chess board square.
	 * @param position Position of the square to be returned.
	 * @return Character, contents of the square.
	 */
	public char getSquareContents(int position) {
		return chessboard[position/8][position%8];
	}
	
	/**
	 * Returns contents of one chess board square.
	 * @param row Row of the square to be returned.
	 * @param col Column of the square to be returned.
	 * @return Character, contents of the square.
	 */
	public char getSquareContents(int row, int col) {
		return chessboard[row][col];
	}
	
	/**
	 * Changes the contents of one square.
	 * @param position Position of the square to be changed.
	 * @param contents New contents of the square.
	 */
	private void setSquare(int position, char contents) {
		chessboard[position/8][position%8] = contents;
	}
	
}
