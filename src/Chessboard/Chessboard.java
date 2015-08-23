/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package Chessboard;

import Chessboard.pieces.Bishop;
import Chessboard.pieces.King;
import Chessboard.pieces.Knight;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import Chessboard.pieces.Queen;
import Chessboard.pieces.Rook;
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
