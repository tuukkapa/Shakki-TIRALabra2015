package AI;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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

	public int max(Chessboard chessboard, int depth) {
		if (depth == 0 || chessboard.isItCheckMate(false)) {
			return chessboard.evaluate();
		}
		int bestValue = Integer.MIN_VALUE;
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(false);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				chessboard.movePiece(move);
				value = min(chessboard, depth - 1);
				if (value > bestValue) {
					bestValue = value;
					bestMove = move;
				}
				chessboard.undoMove(move);
			}
		}
		return bestValue;
	}
	
	public int min(Chessboard chessboard, int depth) {
		if (depth == 0 || chessboard.isItCheckMate(false)) {
			return chessboard.evaluate();
		}
		int bestValue = Integer.MAX_VALUE;
		int value = 0;
		ArrayList<Piece> pieces = chessboard.getPieces(true);
		for (Piece piece : pieces) {
			ArrayList<Move> moves = piece.getPossibleMoves(chessboard);
			for (Move move : moves) {
				chessboard.movePiece(move);
				value = max(chessboard, depth - 1);
				if (value < bestValue) {
					bestValue = value;
				}
				chessboard.undoMove(move);
			}
		}
		return bestValue;
	}
	
	/**
	 * Uses chessboard-objects methods to clone it's board and also pieces into new objects.
	 * @param chessboard Chessboard-object to be cloned.
	 * @return New Chessboard-object, clone of the object and other objects inside it.
	 * @throws CloneNotSupportedException 
	 */
	/*private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Chessboard cloneBoard = new Chessboard();
		cloneBoard.setBoard(chessboard.cloneBoard());
		cloneBoard.setPieces(true, chessboard.clonePieces(true));
		cloneBoard.setPieces(false, chessboard.clonePieces(false));
		return cloneBoard;
	}*/
	
	private Move[] treeMapToArray(Chessboard chessboard, TreeMap<Integer, Piece> pieces, boolean white) {
		int size = 0;
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			size += piece.getValue().getPossibleMoves(chessboard).size();
		}
		Move[] moves = new Move[size];
		int counter = 0;
		for (Map.Entry<Integer, Piece> piece : pieces.entrySet()) {
			ArrayList<Move> movelist = piece.getValue().getPossibleMoves(chessboard);
			for (int i = 0; i < movelist.size(); i++) {
				moves[counter] = movelist.get(i);
				counter++;
			}
		}
		return moves;
	}
	
}
