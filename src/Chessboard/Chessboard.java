/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard;

import Chessboard.pieces.*;
import DataStructures.List;

/**
 * Instances of this class contain a two dimensional Piece-object array, which
 * contains the chess pieces situated at the chessboard in formation of the current
 * chess game position.
 * 
 * Instances also contain:
 * - each colour's pieces in a List-object
 * - integer marking the current position of kings
 * - integer containing the amount of current white and black officers
 * 
 * @author Tuukka Paukkunen
 */
public class Chessboard {
	
	private Piece[][] chessboard;
	private List<Piece> whitePieces, blackPieces;
	private int whiteKingPosition, blackKingPosition;
	private int blackOfficers, whiteOfficers;
	
	/**
	 * Constructor
	 */
	public Chessboard() {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
		whiteOfficers = 0;
		blackOfficers = 0;
		char[][] newBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		setBoard(newBoard);
	}
	
	/**
	 * Constructor, clones the board given as a parameter.
	 * @param anotherboard Chessboard-object from which the contents is cloned.
	 * @throws CloneNotSupportedException 
	 */
	public Chessboard(Chessboard anotherboard) throws CloneNotSupportedException {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
		for (int i = 0; i < anotherboard.getListSize(true); i++) {
			int row = anotherboard.getFromList(true, i).getPosition()/8;
			int col = anotherboard.getFromList(true, i).getPosition()%8;
			Piece piece = anotherboard.getFromList(true, i);
			Piece clonedPiece = (Piece)piece.clone();
			if (clonedPiece.getPosition() != -1) {
				chessboard[row][col] = clonedPiece;
				addToList(clonedPiece);
			}
			whiteKingPosition = anotherboard.getKingPosition(true);
			whiteOfficers = anotherboard.getOfficersAmount(true);
		}
		for (int i = 0; i < anotherboard.getListSize(false); i++) {
			int row = anotherboard.getFromList(false, i).getPosition()/8;
			int col = anotherboard.getFromList(false, i).getPosition()%8;
			Piece piece = anotherboard.getFromList(false, i);
			Piece clonedPiece = (Piece)piece.clone();
			if (clonedPiece.getPosition() != -1) {
				chessboard[row][col] = clonedPiece;
				addToList(clonedPiece);
			}
			blackKingPosition = anotherboard.getKingPosition(false);
			blackOfficers = anotherboard.getOfficersAmount(false);
		}
	}
	
	/**
	 * Creates new chessboard from the char-array given as parameter.
	 * @param newboard Two dimensional char-array representing the chessboard.
	 */
	public void setBoard(char[][] newboard) {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
		whiteOfficers = 0;
		blackOfficers = 0;
		for (int i = 0; i < 64; i++) {
			if (newboard[i/8][i%8] == 'p') {
				this.add(new Pawn(false, i));
			} else if (newboard[i/8][i%8] == 'r') {
				this.add(new Rook(false, i));
			} else if (newboard[i/8][i%8] == 'n') {
				this.add(new Knight(false, i));
			} else if (newboard[i/8][i%8] == 'b') {
				this.add(new Bishop(false, i));
			} else if (newboard[i/8][i%8] == 'q') {
				this.add(new Queen(false, i));
			} else if (newboard[i/8][i%8] == 'k') {
				this.add(new King(false, i));
				blackKingPosition = i;
			} else if (newboard[i/8][i%8] == 'P') {
				this.add(new Pawn(true, i));
			} else if (newboard[i/8][i%8] == 'R') {
				this.add(new Rook(true, i));
			} else if (newboard[i/8][i%8] == 'N') {
				this.add(new Knight(true, i));
			} else if (newboard[i/8][i%8] == 'B') {
				this.add(new Bishop(true, i));
			} else if (newboard[i/8][i%8] == 'Q') {
				this.add(new Queen(true, i));
			} else if (newboard[i/8][i%8] == 'K') {
				this.add(new King(true, i));
				whiteKingPosition = i;
			}
		}
	}
	
	/**
	 * Returns the chessboard as two dimensional char-array.
	 * @return Two dimensional char array representing the chessboard.
	 */
	public char[][] getBoardAsCharArray() {
		char[][] newboard = new char[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j] == null) {
					newboard[i][j] = ' ';
				} else {
					newboard[i][j] = chessboard[i][j].getSign();
				}
			}
		}
		return newboard;
	}
	
	/**
	 * Add one piece to the chessboard and the correct List.
	 * @param piece Piece-object, to be added to the board and list.
	 */
	private void add(Piece piece) {
		int position = piece.getPosition();
		int row = piece.getPosition() / 8;
		int col = piece.getPosition() % 8;
		if (piece instanceof King) {
			if (piece.amIWhite()) {
				whiteKingPosition = position;
			} else {
				blackKingPosition = position;
			}
		}
		Piece oldPiece = null;
		chessboard[row][col] = piece;
		addToList(piece);
	}
	
	/**
	 * Switches piece-object into another. Used in promoting pawn to queen.
	 * @param oldPiece Piece-object, old piece to be promoted.
	 * @param newPiece Piece-object, new piece, replaces the old one.
	 * @return 
	 */
	protected boolean switchPiece(Piece oldPiece, Piece newPiece) {
		if (oldPiece == null || newPiece == null) {
			return false;
		}
		if (oldPiece.getPosition() != newPiece.getPosition()) {
			return false;
		}
		// if oldPiece isn't on this board
		if (!this.getSquareContents(oldPiece.getPosition()).equals(oldPiece)) {
			return false;
		}
		int row = oldPiece.getPosition() / 8;
		int col = oldPiece.getPosition() % 8;
		chessboard[row][col] = newPiece;
		if (newPiece.amIWhite()) {
			whitePieces.switchElement(oldPiece, newPiece);
		} else {
			blackPieces.switchElement(oldPiece, newPiece);
		}
		return true;
	}
	
	/**
	 * Removes piece from the position mentioned in the parameter, if piece is
	 * present at the position.
	 * @param position Integer, 0 = top left, 63 = bottom right.
	 */
	/*protected void remove(int position) {
		if (position < 0 || position > 63) {
			return;
		}
		int row = position / 8;
		int col = position % 8;
		Piece piece = chessboard[row][col];
		removeFromList(piece);
		chessboard[row][col] = null;	
	}*/
	
	/**
	 * Updates piece's position at the board. Piece to be moved is at the startPosition.
	 * @param startPosition Integer, position of the piece, 0 = top left, 63 = bottom right.
	 * @param endPosition Integer, position, where the piece is to be moved. 0 = top left, 63 = bottom right.
	 * @return Boolean, true, if update is successful, false otherwise.
	 */
	protected boolean updatePiecePosition(int startPosition, int endPosition) {
		if (startPosition < 0 || startPosition > 63 || endPosition < 0 || endPosition > 63) {
			return false;
		}
		if (this.getSquareContents(startPosition) == null) {
			return false;
		}
		
		int startRow = startPosition / 8;
		int startCol = startPosition % 8;
		int endRow = endPosition / 8;
		int endCol = endPosition % 8;
		
		Piece piece = this.getSquareContents(startPosition);
		Piece capturedPiece = this.getSquareContents(endPosition);
		
		// move the piece
		chessboard[startRow][startCol] = null;
		chessboard[endRow][endCol] = piece;
		piece.setPosition(endPosition);
		if (piece instanceof King) {
			if (piece.amIWhite()) {
				whiteKingPosition = endPosition;
			} else {
				blackKingPosition = endPosition;
			}
		}
		
		// move captured piece outside the board, if it exists
		if (capturedPiece != null) {
			capturedPiece.setPosition(-1);	
		}
		return true;
	}
	
	/**
	 * Updates piece's position at the board. Piece to be moved is at the startPosition.
	 * @param piece Piece-object, which information (e.g. position) is to be updated.
	 * @param endPosition Integer, position, where the piece is to be moved. 0 = top left, 63 = bottom right.
	 * @return Boolean, true, if update is successful, false otherwise.
	 */
	protected boolean updatePiecePosition(Piece piece, int endPosition) {
		if (endPosition < 0 || endPosition > 63) {
			return false;
		}
		if (piece == null) {
			return false;
		}
		
		int endRow = endPosition / 8;
		int endCol = endPosition % 8;
		
		// move the piece
		chessboard[endRow][endCol] = piece;
		piece.setPosition(endPosition);
		return true;
	}
	
	/**
	 * Returns one piece from the correct list.
	 * @param white Boolean, true is white, false is black.
	 * @param index Index of the piece on the list.
	 * @return Piece-object.
	 */
	public Piece getFromList(boolean white, int index) {
		if (white) {
			return whitePieces.get(index);
		} else {
			return blackPieces.get(index);
		}
	}
	
	/**
	 * Returns contents of one chessboard square, e.g. Piece, if piece is present,
	 * otherwise null.
	 * @param row Integer, row of the contents.
	 * @param col Integer, column of the contents.
	 * @return Piece-object.
	 */
	public Piece getSquareContents(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return null;
		}
		return chessboard[row][col];
	}
	
	/**
	 * Returns contents of one chessboard square, e.g. Piece, if piece is present,
	 * otherwise null.
	 * @param position Position of the contents. 0 = top left, 63 = bottom right.
	 * @return Piece-object.
	 */
	public Piece getSquareContents(int position) {
		if (position < 0 || position > 63) {
			return null;
		} else {
			return chessboard[position/8][position%8];
		}
	}
	
	/**
	 * Returns position of the king with the colour mentioned in the parameter.
	 * @param white Boolean, white is true, black is false.
	 * @return Integer, Position of the king. 0 = top left, 63 = bottom right.

	 */
	public int getKingPosition(boolean white) {
		return white ? whiteKingPosition : blackKingPosition;
	}
	
	/**
	 * Returns size of the list mentioned in the parameter.
	 * @param white Boolean, white is true, black is false.
	 * @return Integer, size of the list.
	 */
	public int getListSize(boolean white) {
		return white ? whitePieces.size() : blackPieces.size();
	}
	
	/**
	 * Returns amount of officers of the colour specified at the parameter currently on board.
	 * @param white Boolean, true is white, false is black.
	 * @return Integer, amount of officers.
	 */
	public int getOfficersAmount(boolean white) {
		return white ? whiteOfficers : blackOfficers;
	}
	
	/**
	 * Adds the piece to the list. The correct list is selected by the piece's
	 * white-attribute.
	 * @param piece Piece to be added to the list.
	 */
	private void addToList(Piece piece) {
		if (piece.amIWhite()) {
			whitePieces.add(piece);
			if (!(piece instanceof Pawn)) {
				whiteOfficers++;
			}
		} else {
			blackPieces.add(piece);
			if (!(piece instanceof Pawn)) {
				blackOfficers++;
			}
		}
	}

}
