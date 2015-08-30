package AI;


import Chessboard.pieces.Piece;
import Chessboard.*;
import DataStructures.List;

/**
 * This class does everything related to the game tree.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class AI {
	
	private Move bestMove;
	private int originalDepth;
	private boolean white;
	
	public AI(boolean white) {
		this.white = white;
	}
	
	/**
	 * Returns boolean value, which colour this AI plays.
	 * @return Boolean, true is white, false is black.
	 */
	public boolean getColour() {
		return white;
	}
	
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
		max(Integer.MIN_VALUE, Integer.MAX_VALUE, chessboard, depth, white);
		if (ChessboardHandler.isItCheckMate(chessboard) >= 0) {
			return new Move(0, 0);
		} else {
			return bestMove;
		}
	}

	/**
	 * Maximizing player's part of minimax-algorithm.
	 * @param alpha Integer, maximizing player's best (i.e. highest) value so far.
	 * @param beta Integer, minimizing player's best (i.e. lowest) value so far.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @param white Boolean, which colour this AI plays. True is white, false is black.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	private int max(int alpha, int beta, Chessboard chessboard, int depth, boolean white) throws CloneNotSupportedException {
		if (depth == 0 || ChessboardHandler.isItCheckMate(chessboard) >= 0) {
			return Evaluate.evaluate(chessboard, white);
		}
		int score = 0;
		for (int i = 0; i < chessboard.getListSize(white); i++) {
			Piece piece = chessboard.getFromList(white, i);
			if (piece.getPosition() == -1) {
				continue;
			}
			List<Move> moves = piece.getPossibleMoves(chessboard);
			for (int j = 0; j < moves.size(); j++) {
				if (bestMove == null) {
					bestMove = moves.get(j);
				}
				Chessboard cloneBoard = new Chessboard(chessboard);
				ChessboardHandler.movePiece(cloneBoard, moves.get(j));
				score = min(alpha, beta, cloneBoard, depth - 1, white);
				/*if (depth == originalDepth) {
					System.out.println(piece.getSign() + " " + (char)(piece.getPosition()%8+65) + (8-(piece.getPosition()/8)) + 
							"->" + (char)(moves.get(j).getEnd()%8+65) + (8-(moves.get(j).getEnd()/8)) + ": arvosana " + score);
				}*/
				if (score > alpha) {
					alpha = score;
					if (depth == originalDepth) {
						bestMove = moves.get(j);
					}
				}
				if (alpha >= beta) {
					return alpha;
				}
			}
		}
		return alpha;
	}
	
	/**
	 * Minimizing player's part of minimax-algorithm.
	 * @param alpha Integer, maximizing player's best (i.e. highest) value so far.
	 * @param beta Integer, minimizing player's best (i.e. lowest) value so far.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @param white Boolean, which colour this AI plays. True is white, false is black.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	private int min(int alpha, int beta, Chessboard chessboard, int depth, boolean white) throws CloneNotSupportedException {
		if (depth == 0 || ChessboardHandler.isItCheckMate(chessboard) >= 0) {
			return Evaluate.evaluate(chessboard, white);
		}
		int score = 0;
		for (int i = 0; i < chessboard.getListSize(!white); i++) {
			Piece piece = chessboard.getFromList(!white, i);
			if (piece.getPosition() == -1) {
				continue;
			}
			List<Move> moves = piece.getPossibleMoves(chessboard);
			for (int j = 0; j < moves.size(); j++) {
				Chessboard cloneBoard = new Chessboard(chessboard);
				ChessboardHandler.movePiece(cloneBoard, moves.get(j));
				score = max(alpha, beta, cloneBoard, depth - 1, white);
				if (score < beta) {
					beta = score;
				}
				if (alpha >= beta) {
					return beta;
				}
			}
		}
		return beta;
	}
	
}
