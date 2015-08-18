package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;

/**
 * This class does everything related to the game tree.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class AI {
	
	private Move bestMove;
	
	public AI() {
		bestMove = null;
	}
	
	public Move getMove(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		bestMove = null;
		max(chessboard, depth);
		return bestMove;
	}

	public int max(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate(false)) {
			return chessboard.evaluate();
		}
		int bestValue = Integer.MIN_VALUE;
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(false);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = this.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				value = min(cloneBoard, depth - 1);
				if (value > bestValue) {
					bestValue = value;
					bestMove = move;
				}
			}
		}
		return bestValue;
	}
	
	public int min(Chessboard chessboard, int depth) throws CloneNotSupportedException {
		if (depth == 0 || chessboard.isItCheckMate(false)) {
			return chessboard.evaluate();
		}
		int bestValue = Integer.MAX_VALUE;
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(true);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				Chessboard cloneBoard = this.cloneBoardAndPieces(chessboard);
				cloneBoard.movePiece(move);
				value = max(cloneBoard, depth - 1);
				if (value < bestValue) {
					bestValue = value;
				}
			}
		}
		return bestValue;
	}
	
	private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Piece[][] cloneArray = chessboard.cloneBoard();
		Chessboard cloneObject = new Chessboard();
		cloneObject.setBoard(cloneArray);
		return cloneObject;
	}
	
}
