/*
 * Copyright (C) 2015
 * 
 * Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */

package AI;

import Chessboard.pieces.SuperPiece;
import Chessboard.Chessboard;
import Chessboard.ChessboardHandler;
import Chessboard.pieces.*;

/**
 * Concept of this evaluation method is by Tomasz Michniewski.
 * I have added modifications of my own, such as piece protection and attacking
 * status.
 * (https://chessprogramming.wikispaces.com/Simplified+evaluation+function)
 * 
 * Method determines the game situation's value from black pieces' point of view.
 * Higher value means higher chances at winning the game.
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
	
	// E2 is -1000 for trying to convince AI to start with pawn at E2
	private static final int[][] PAWN_MAP = {
		{ 0,  0,  0,  0,    0,  0,  0,  0},
		{50, 50, 50, 50,   50, 50, 50, 50},
		{10, 10, 20, 30,   30, 20, 10, 10},
		{ 5,  5, 10, 25,   25, 10,  5,  5},
		{ 5,  0,  0, 10,   20,  0,  0,  5},
		{ 5, -5,-10,  0,    0,-10, -5,  5},
		{ 0, 10, 10,-20,-1000, 10, 10,  0},
		{ 0,  0,  0,  0,    0,  0,  0,  0}
	};
	private static final int[][] KNIGHT_MAP = {
		{-50,-40,-30,-30,-30,-30,-40,-50},
		{-40,-20,  0,  0,  0,  0,-20,-40},
		{-30,  0, 10, 15, 15, 10,  0,-30},
		{-30,  5, 15, 20, 20, 15,  5,-30},
		{-30,  0, 15, 20, 20, 15,  0,-30},
		{-30,  5, 10, 15, 15, 10,  5,-30},
		{-40,-20,  0,  5,  5,  0,-20,-40},
		{-50,  0,-30,-30,-30,-30,  0,-50}
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
		{-20,-10,-10,100, -5,-10,-10,-20}
	};
	private static final int[][] KING_MAP = {
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-30,-40,-40,-50,-50,-40,-40,-30},
		{-20,-30,-30,-40,-40,-30,-30,-20},
		{-10,-20,-20,-20,-20,-20,-20,-10},
		{ 20, 20,-20,-20,-20,-20, 20, 20},
		{ 20, 30, 50,  0,  0, 10, 50, 20}
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
	
	/**
	 * Determines the game situation's value from the point of view given as a parameter.
	 * Higher points means better chances to winning.
	 * @param chessboard Chessboard-object, which game is being evaluated.
	 * @param white Boolean, from which colour's point of view the position is evaluated.
	 * @param variance Integer, how much more or less the gameSituationPoints randomly is from the correct value.
	 * @return Integer, value of the game situation.
	 */
	public static int evaluate(Chessboard chessboard, boolean white, int variance) {
		int gameSituationPoints = 0;
		
		gameSituationPoints -= calculatePointsFromPieces(chessboard, !white);
		gameSituationPoints += calculatePointsFromPieces(chessboard, white);
		
		// If it's not end game, add random number to gameSituationPoints to give variety to the game.
		if (!(isItEndGame(chessboard, true) || isItEndGame(chessboard, false))) {
			gameSituationPoints += Tools.randInt(variance);
		}
		
		return gameSituationPoints;
	}
		
	/**
	 * Calculates game situation points from one player's pieces.
	 * @param chessboard Chessboard-object, which game is on.
	 * @param white Boolean, side of the player. True is white, false is black.
	 * @return Integer, value of the game situation for the colour in the parameter.
	 */
	private static int calculatePointsFromPieces(Chessboard chessboard, boolean white) {
		int row = 0, col = 0;
		int gameSituationPoints = 0;
		boolean isItEndGame = white ? isItEndGame(chessboard, true) : isItEndGame(chessboard, false);
		
		if (ChessboardHandler.isItCheck(chessboard, white)) {
			gameSituationPoints -= 500;
			int thisSideIsCheckmated = white ? 1 : 0;
			if (ChessboardHandler.isItCheckMate(chessboard) == thisSideIsCheckmated) {
				return -100000;
			}
		}
		
		SuperPiece superPiece = new SuperPiece(white, 0);
		
		for (int i = 0; i < chessboard.getListSize(white); i++) {
			Piece piece = chessboard.getFromList(white, i);
			superPiece.setPosition(piece.getPosition());
			boolean pieceProtected = superPiece.getProtectionStatus(chessboard, true);
			boolean pieceAttacked = superPiece.getProtectionStatus(chessboard, false);
			if (piece.getPosition() == -1) {
				continue;
			}
			row = white ? piece.getPosition() / 8 : 7 - (piece.getPosition() / 8);
			col = piece.getPosition() % 8;
			if (piece instanceof Pawn) {
				gameSituationPoints += PAWN_VALUE + PAWN_MAP[row][col];
				gameSituationPoints += pieceProtected ? PAWN_VALUE/2 : 0;
				gameSituationPoints -= pieceAttacked ? PAWN_VALUE/2 : 0;
				if (piece.getHasMoved()) {
					gameSituationPoints -= !pieceProtected && !pieceAttacked ? PAWN_VALUE/2 : 0;
				}
			}
			if (piece instanceof Knight) {
				gameSituationPoints += KNIGHT_VALUE + KNIGHT_MAP[row][col];
				gameSituationPoints += pieceProtected ? KNIGHT_VALUE/2 : 0;
				gameSituationPoints -= pieceAttacked ? KNIGHT_VALUE/2 : 0;
				if (piece.getHasMoved()) {
					gameSituationPoints -= !pieceProtected && !pieceAttacked ? KNIGHT_VALUE/2 : 0;
				}
			}
			if (piece instanceof Bishop) {
				gameSituationPoints += BISHOP_VALUE + BISHOP_MAP[row][col];
				gameSituationPoints += pieceProtected ? BISHOP_VALUE/2 : 0;
				gameSituationPoints -= pieceAttacked ? BISHOP_VALUE/2 : 0;
				if (piece.getHasMoved()) {
					gameSituationPoints -= !pieceProtected && !pieceAttacked ? BISHOP_VALUE/2 : 0;
				}
			}
			if (piece instanceof Rook) {
				gameSituationPoints += ROOK_VALUE + ROOK_MAP[row][col];
				gameSituationPoints += pieceProtected ? ROOK_VALUE/2 : 0;
				gameSituationPoints -= pieceAttacked ? ROOK_VALUE/2 : 0;
				if (piece.getHasMoved()) {
					gameSituationPoints -= !pieceProtected && !pieceAttacked ? ROOK_VALUE/2 : 0;
				}
			}
			if (piece instanceof Queen) {
				gameSituationPoints += QUEEN_VALUE + QUEEN_MAP[row][col];
				gameSituationPoints += pieceProtected ? QUEEN_VALUE/2 : 0;
				// Queen gets more severe penalty if left attacked or unprotected
				gameSituationPoints -= pieceAttacked ? QUEEN_VALUE : 0;
				if (piece.getHasMoved()) {
					gameSituationPoints -= !pieceProtected && !pieceAttacked ? QUEEN_VALUE : 0;
				}
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
		return chessboard.getOfficersAmount(white) < 5;
	}

}
