/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard;

import Chessboard.pieces.*;
import DataStructures.List;

public class Chessboard {
	
	private Piece[][] chessboard;
	private List<Piece> whitePieces, blackPieces;
	private int whiteKingPosition, blackKingPosition;
	private int numberOfBlackOfficers, numberOfWhiteOfficers; // TODO make support for this
	
	public Chessboard() {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
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
	
	public Chessboard(Chessboard anotherboard) throws CloneNotSupportedException {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
		for (int i = 0; i < anotherboard.getListSize(true); i++) {
			int row = anotherboard.getFromList(true, i).getPosition()/8;
			int col = anotherboard.getFromList(true, i).getPosition()%8;
			Piece piece = anotherboard.getFromList(true, i);
			chessboard[row][col] = (Piece)piece.clone();
			addToList(chessboard[row][col]);
			whiteKingPosition = anotherboard.getKingPosition(true);
		}
		for (int i = 0; i < anotherboard.getListSize(false); i++) {
			int row = anotherboard.getFromList(false, i).getPosition()/8;
			int col = anotherboard.getFromList(false, i).getPosition()%8;
			Piece piece = anotherboard.getFromList(false, i);
			chessboard[row][col] = (Piece)piece.clone();
			addToList(chessboard[row][col]);
			blackKingPosition = anotherboard.getKingPosition(false);
		}
	}
	
	/**
	 * Creates new chessboard from the char-array given as parameter.
	 * @param newboard Two dimensional char-array representing the chessboard.
	 * @return New Chessboard-object.
	 */
	public void setBoard(char[][] newboard) {
		chessboard = new Piece[8][8];
		whitePieces = new List<>();
		blackPieces = new List<>();
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
	 * Adds a piece to the position on the chessboard, where the piece's position
	 * attribute says, piece should be situated.
	 * @param piece Piece-object to be added to the chessboard.
	 */
	protected void add(Piece piece) {
		if (piece == null) {
			return;
		}
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
		if (chessboard[row][col] != null) {
			oldPiece = chessboard[row][col];
			removeFromList(oldPiece);
		}
		chessboard[row][col] = piece;
		addToList(piece);
	}
	
	/**
	 * Removes piece from the position on the parameter.
	 * @param position Integer, position on the board. 0 = top left, 63 = bottom right.
	 */
	protected void remove(int position) {
		if (position < 0 || position > 63) {
			return;
		}
		int row = position / 8;
		int col = position % 8;
		Piece piece = chessboard[row][col];
		removeFromList(piece);
		chessboard[row][col] = null;	
	}
	
	/**
	 * Returns Piece-object from the list told at the white-parameter.
	 * @param white Boolean, colour of the piece. True is white, false is black.
	 * @param index Integer, index of the piece on the list.
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
	 * Returns contents of one chessboard square.
	 * @param row Integer, row of the contents.
	 * @param col Integer, column of the contents.
	 * @return Piece-object or null, if the square is empty.
	 */
	public Piece getSquareContents(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return null;
		}
		return chessboard[row][col];
	}
	
	/**
	 * Returns contents of one chessboard square.
	 * @param position Integer, position on the board. 0 = top left, 63 = bottom right.
	 * @return Piece-object or null, if the square is empty.
	 */
	public Piece getSquareContents(int position) {
		if (position < 0 || position > 63) {
			return null;
		} else {
			return chessboard[position/8][position%8];
		}
	}
	
	/**
	 * Returns the position of the king on chessboard.
	 * @param white Boolean, colour of the king. True is white, false is black.
	 * @return Integer, position on the board. 0 = top left, 63 = bottom right.
	 */
	public int getKingPosition(boolean white) {
		return white ? whiteKingPosition : blackKingPosition;
	}
	
	/**
	 * Returns size of the chess piece list.
	 * @param white Boolean, colour of the piece list. True is white, false is black.
	 * @return Integer, size of the piece list.
	 */
	public int getListSize(boolean white) {
		return white ? whitePieces.size() : blackPieces.size();
	}
	
	/**
	 * Adds one piece to the correct list. The correct list is determined
	 * from the Piece's colour-attribute.
	 * @param piece Piece-object to be added to the list.
	 */
	private void addToList(Piece piece) {
		if (piece.amIWhite()) {
			whitePieces.add(piece);
		} else {
			blackPieces.add(piece);
		}
	}
	
	/**
	 * Removes one Piece-object from the correct list.
	 * @param piece Piece-object to be removed to the list.
	 */
	private void removeFromList(Piece piece) {
		if (piece.amIWhite()) {
			whitePieces.remove(piece);
		} else {
			blackPieces.remove(piece);
		}
	}

}
