/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard;

import Chessboard.pieces.King;
import Chessboard.pieces.Piece;
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
		}
		for (int i = 0; i < anotherboard.getListSize(false); i++) {
			int row = anotherboard.getFromList(false, i).getPosition()/8;
			int col = anotherboard.getFromList(false, i).getPosition()%8;
			Piece piece = anotherboard.getFromList(false, i);
			chessboard[row][col] = (Piece)piece.clone();
			addToList(chessboard[row][col]);
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
	
	protected void add(Piece piece, int row, int col) {
		if (piece == null) {
			return;
		}
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return;
		}
		if (piece instanceof King) {
			if (piece.amIWhite()) {
				whiteKingPosition = row * 8 + col;
			} else {
				blackKingPosition = row * 8 + col;
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
	
	protected void remove(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return;
		}
		Piece piece = chessboard[row][col];
		removeFromList(piece);
		chessboard[row][col] = null;	
	}
	
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
	
	public Piece getFromList(boolean white, int index) {
		if (white) {
			return whitePieces.get(index);
		} else {
			return blackPieces.get(index);
		}
	}
	
	public Piece getSquareContents(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return null;
		}
		return chessboard[row][col];
	}
	
	public Piece getSquareContents(int position) {
		if (position < 0 || position > 63) {
			return null;
		} else {
			return chessboard[position/8][position%8];
		}
	}
	
	public int getKingPosition(boolean white) {
		return white ? whiteKingPosition : blackKingPosition;
	}
	
	public int getListSize(boolean white) {
		return white ? whitePieces.size() : blackPieces.size();
	}
	
	private void addToList(Piece piece) {
		if (piece.amIWhite()) {
			whitePieces.add(piece);
		} else {
			blackPieces.add(piece);
		}
	}
	
	private void removeFromList(Piece piece) {
		if (piece.amIWhite()) {
			whitePieces.remove(piece);
		} else {
			blackPieces.remove(piece);
		}
	}

}
