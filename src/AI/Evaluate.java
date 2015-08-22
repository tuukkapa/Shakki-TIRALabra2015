/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package AI;

import Chessboard.Chessboard;
import Chessboard.pieces.*;
import java.util.ArrayList;

/**
 * Concept of this evaluation method is by Tomasz Michniewski.
 * (https://chessprogramming.wikispaces.com/Simplified+evaluation+function)
 * 
 * Method determines the game situations value from black pieces' point of view.
 * Higher value means higher chances to winning the game.
 * 
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class Evaluate {
	
	private final static int PAWN_VALUE = 100;
	private final static int KNIGHT_VALUE = 320;
	private final static int BISHOP_VALUE = 330;
	private final static int ROOK_VALUE = 500;
	private final static int QUEEN_VALUE = 900;
	private final static int KING_VALUE = 20000;
	
	private static final int[][] PAWN_MAP = {
		{ 0,  0,  0,  0,  0,  0,  0,  0},
		{50, 50, 50, 50, 50, 50, 50, 50},
		{10, 10, 20, 30, 30, 20, 10, 10},
		{ 5,  5, 10, 25, 25, 10,  5,  5},
		{ 0,  0,  0, 20, 20,  0,  0,  0},
		{ 5, -5,-10,  0,  0,-10, -5,  5},
		{ 5, 10, 10,-20,-20, 10, 10,  5},
		{ 0,  0,  0,  0,  0,  0,  0,  0}
	};
	private static final int[][] KNIGHT_MAP = {
		{-50,-40,-30,-30,-30,-30,-40,-50},
		{-40,-20,  0,  0,  0,  0,-20,-40},
		{-30,  0, 10, 15, 15, 10,  0,-30},
		{-30,  5, 15, 20, 20, 15,  5,-30},
		{-30,  0, 15, 20, 20, 15,  0,-30},
		{-30,  5, 10, 15, 15, 10,  5,-30},
		{-40,-20,  0,  5,  5,  0,-20,-40},
		{-50,-40,-30,-30,-30,-30,-40,-50}
	};
	private static final int[][] BISHOP_MAP = {
		{-20,-10,-10,-10,-10,-10,-10,-20},
		{-10,  0,  0,  0,  0,  0,  0,-10},
		{-10,  0,  5, 10, 10,  5,  0,-10},
		{-10,  5,  5, 10, 10,  5,  5,-10},
		{-10,  0, 10, 10, 10, 10,  0,-10},
		{-10, 10, 10, 10, 10, 10, 10,-10},
		{-10,  5,  0,  0,  0,  0,  5,-10},
		{-20,-10,-10,-10,-10,-10,-10,-20}
	};
	private static final int[][] ROOK_MAP = {
		{  0,  0,  0,  0,  0,  0,  0,  0},
		{  5, 10, 10, 10, 10, 10, 10,  5},
		{ -5,  0,  0,  0,  0,  0,  0, -5},
		{ -5,  0,  0,  0,  0,  0,  0, -5},
		{ -5,  0,  0,  0,  0,  0,  0, -5},
		{ -5,  0,  0,  0,  0,  0,  0, -5},
		{ -5,  0,  0,  0,  0,  0,  0, -5},
		{  0,  0,  0,  5,  5,  0,  0,  0}
	};
	private static final int[][] QUEEN_MAP = {
		{-20,-10,-10, -5, -5,-10,-10,-20},
		{-10,  0,  0,  0,  0,  0,  0,-10},
		{-10,  0,  5,  5,  5,  5,  0,-10},
		{ -5,  0,  5,  5,  5,  5,  0, -5},
		{  0,  0,  5,  5,  5,  5,  0, -5},
		{-10,  5,  5,  5,  5,  5,  0,-10},
		{-10,  0,  5,  0,  0,  0,  0,-10},
		{-20,-10,-10, -5, -5,-10,-10,-20}
	};
	private static final int[][] KING_MAP = {
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-20,-30,-30,-40,-40,-30,-30,-20},
		{-10,-20,-20,-20,-20,-20,-20,-10},
		{ 20, 20,  0,  0,  0,  0, 20, 20},
		{ 20, 30, 10,  0,  0, 10, 30, 20}
	};
	private static final int[][] KING_END_GAME_MAP = {
		{-50,-40,-30,-20,-20,-30,-40,-50},
		{-30,-20,-10,  0,  0,-10,-20,-30},
		{-30,-10, 20, 30, 30, 20,-10,-30},
		{-30,-10, 30, 40, 40, 30,-10,-30},
		{-30,-10, 30, 40, 40, 30,-10,-30},
		{-30,-10, 20, 30, 30, 20,-10,-30},
		{-30,-30,  0,  0,  0,  0,-30,-30},
		{-50,-30,-30,-30,-30,-30,-30,-50}
	};
	private static boolean endGameForWhite, endGameForBlack;
	
	/**
	 * Determines the game situation's value from the black player's point of view.
	 * Higher points means better chances to winning.
	 * @param chessboard Chessboard-object, which game is being evaluated.
	 * @return Integer, value of the game situation.
	 */
	public static int evaluate(Chessboard chessboard) {
		endGameForWhite = isItEndGame(chessboard, true);
		endGameForBlack = isItEndGame(chessboard, false);
		
		int gameSituationPoints = 0;
		
		gameSituationPoints -= calculatePointsFromPieces(chessboard, true); // points for white
		gameSituationPoints += calculatePointsFromPieces(chessboard, false); // points for black
		
		return gameSituationPoints;
	}
	
	/**
	 * Calculates game situation points from one player's pieces.
	 * @param chessboard Chessboard-object, which game is on.
	 * @param white Boolean, side of the player. True is white, false is black.
	 * @return Integer, value of the game situation for the colour in the parameter..
	 */
	private static int calculatePointsFromPieces(Chessboard chessboard, boolean white) {
		ArrayList<Piece> pieces = chessboard.getPieces(white);
		int row = 0, col = 0;
		int gameSituationPoints = 0;
		boolean isItEndGame = white ? endGameForWhite : endGameForBlack;
		
		if (chessboard.isItCheck(white)) {
			gameSituationPoints -= 5000;
			int thisSideIsCheckmated = white ? 1 : 0;
			if (chessboard.isItCheckMate() == thisSideIsCheckmated) {
				return -100000;
			}
		}
		
		for (Piece piece : pieces) {
			row = white ? piece.getPosition() / 8 : 7 - (piece.getPosition() / 8);
			col = piece.getPosition() % 8;
			if (piece instanceof Pawn) {
				gameSituationPoints += PAWN_VALUE + PAWN_MAP[row][col];
			}
			if (piece instanceof Knight) {
				gameSituationPoints += KNIGHT_VALUE + KNIGHT_MAP[row][col];
			}
			if (piece instanceof Bishop) {
				gameSituationPoints += BISHOP_VALUE + BISHOP_MAP[row][col];
			}
			if (piece instanceof Rook) {
				gameSituationPoints += ROOK_VALUE + ROOK_MAP[row][col];
			}
			if (piece instanceof Queen) {
				gameSituationPoints += QUEEN_VALUE + QUEEN_MAP[row][col];
			}
			if (piece instanceof King) {
				if (isItEndGame) {
					gameSituationPoints += KING_VALUE + KING_END_GAME_MAP[row][col];
				} else {
					gameSituationPoints += KING_VALUE + KING_MAP[row][col];
				}
			}
		}
		return gameSituationPoints;
	}
	
	/**
	 * Determines, whether it is endgame for the side told at parameter "white"
	 * @param chessboard Chessboard-object, which game is played.
	 * @param white Side of the player. True is white, false is black.
	 * @return True, if it is end game for the side, false otherwise.
	 */
	private static boolean isItEndGame(Chessboard chessboard, boolean white) {
		ArrayList<Piece> pieces = chessboard.getPieces(white);
		int officers = 0;
		for (Piece piece : pieces) {
			if (!(piece instanceof Pawn )) {
				officers++;
			}
		}
		return officers < 5;
	}

}
