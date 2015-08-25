package AI;


import Chessboard.pieces.Piece;
import Chessboard.*;
import java.util.ArrayList;

/**
 * This class does everything related to the game tree.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class AI {
	
	private Move bestMove;
	private int originalDepth;
	
	/**
	 * Starts the minimax-algorithm and returns computer's next move.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @return Move-object, includes start- and end-coordinates and possibly a captured piece.
	 * @throws CloneNotSupportedException 
	 */
	public Move getMove(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		bestMove = null; // remove previous move command
		originalDepth = depth;
		max(Integer.MIN_VALUE, Integer.MAX_VALUE, chessboard, depth);
		if (ChessboardHandler.isItCheckMate(chessboard) == 0) {
			return new Move(0, 0);
		} else {
			return bestMove;
		}
	}

	/**
	 * Maximizing player's part of minimax-algorithm.
	 * @param alpha Integer, maximizing player's best option (i.e. highest value) so far.
	 * @param beta Integer, minimizing player's best option (i.e. lowest value) so far.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	private int max(int alpha, int beta, Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || ChessboardHandler.isItCheckMate(chessboard) >= 0) {
			return Evaluate.evaluate(chessboard);
		}
		int bestValue = Integer.MIN_VALUE, score = 0;	
		for (int i = 0; i < chessboard.getListSize(false); i++) {
			Piece piece = chessboard.getFromList(false, i);
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = new Chessboard(chessboard);
				ChessboardHandler.movePiece(cloneBoard, move);
				score = min(alpha, beta, cloneBoard, depth - 1);
				if (depth == originalDepth) {
					System.out.println(piece.getSign() + " " + (char)(piece.getPosition()%8+65) + (8-(piece.getPosition()/8)) + 
							"->" + (char)(move.getEnd()%8+65) + (8-(move.getEnd()/8)) + ": arvosana " + score);
				}
				if (bestValue < score) {
					bestValue = score;
					if (depth == originalDepth) {
						bestMove = move;
					}
				}
				if (bestValue >= beta) {
					return bestValue;
				}
				alpha = Math.max(alpha, bestValue);
			}
		}
		return bestValue;
	}
	
	/**
	 * Minimizing player's part of minimax-algorithm.
	 * @param alpha Integer, maximizing player's best option (i.e. highest value) so far.
	 * @param beta Integer, minimizing player's best option (i.e. lowest value) so far.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	private int min(int alpha, int beta, Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || ChessboardHandler.isItCheckMate(chessboard) >= 0) {
			return Evaluate.evaluate(chessboard);
		}
		int bestValue = Integer.MAX_VALUE, score = 0;
		for (int i = 0; i < chessboard.getListSize(true); i++) {
			Piece piece = chessboard.getFromList(true, i);
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = new Chessboard(chessboard);
				ChessboardHandler.movePiece(cloneBoard, move);
				score = max(alpha, beta, cloneBoard, depth - 1);
				if (bestValue > score) {
					bestValue = score;
				}
				if (bestValue <= alpha) {
					return bestValue;
				}
				beta = Math.min(beta, bestValue);
			}
		}
		return bestValue;
	}
	
}
