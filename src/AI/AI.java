package AI;


import Chessboard.Move;
import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;

/**
 * This class does everything related to the game tree.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class AI {
	
	private Move bestMove;
	private int originalDepth;
	
	public AI() {
		bestMove = null;
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
		max(Integer.MIN_VALUE, Integer.MAX_VALUE, chessboard, depth);
		return bestMove;
	}

	/**
	 * Maximizing player's part of minimax-algorithm.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	public int max(int alpha, int beta, Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate() >= 0) {
			return Evaluate.evaluate(chessboard);
		}
		int value = Integer.MIN_VALUE, score = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(false);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				if (bestMove == null) {
					bestMove = move;
				}
				Chessboard cloneBoard = chessboard.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				score = min(alpha, beta, cloneBoard, depth - 1);
				// chessprogramming.wikispaces.com
				/*if(score >= beta)
					return beta;
				if(score > alpha) {
					alpha = score; // alpha acts like max in MiniMax
					if (depth == originalDepth) {
						bestMove = move;
					}
				}*/
				// Helsingin yo
				if (score > value) {
					value = score;
					if (depth == originalDepth) {
						bestMove = move;
					}
				}
				if (value >= beta) {
					return value;
				}
				alpha = Math.max(alpha, value);
			}
		}
		return value; // Helsingin yo
		//return alpha; // chessprogramming.wikispaces.com
	}
	
	/**
	 * Minimizing player's part of minimax-algorithm.
	 * @param chessboard Chessboard, which the game is on.
	 * @param depth Integer, how many levels the game tree is built.
	 * @return Integer, value of the game situation.
	 * @throws CloneNotSupportedException 
	 */
	public int min(int alpha, int beta, Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate() >= 0) {
			return Evaluate.evaluate(chessboard);
		}
		int value = Integer.MAX_VALUE, score = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(true);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = chessboard.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				score = max(alpha, beta, cloneBoard, depth - 1);
				// chessprogramming.wikispaces.com
				/*if(score <= alpha)
					return alpha;
				if(score < beta)
					beta = score;*/
				// Helsingin yo
				if (score < value) {
					value = score;
				}
				if (value <= alpha) {
					return alpha;
				}
				beta = Math.min(beta, value);
			}
		}
		return value; // Helsingin yo
		//return beta; // chessprogramming.wikispaces.com
	}
	
}
