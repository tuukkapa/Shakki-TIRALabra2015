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
		bestMove = null;
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
			return -Evaluate.evaluate(chessboard);
		}
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(false);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				if (bestMove == null) {
					bestMove = move;
				}
				Chessboard cloneBoard = chessboard.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				value = min(alpha, beta, cloneBoard, depth - 1);
				if (value >= beta)  {
					return beta;
				}
				if (value > alpha) {
					alpha = value;
					if (depth == originalDepth) {
						bestMove = move;
					}
				}
			}
		}
		return alpha;
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
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(true);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = chessboard.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				value = max(alpha, beta, cloneBoard, depth - 1);
				if (value <= alpha) {
					return alpha;
				}
				if (value < beta) {
					beta = value;
				}
			}
		}
		return beta;
	}
	
	
	
}
