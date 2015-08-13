
import Chessboard.Chessboard;
import Chessboard.pieces.Pawn;
import Chessboard.pieces.Piece;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuukka
 */
public class AI {
	
	/* Minimax in pseudocode copied from Wikipedia:
	
	function minimax(node, depth, maximizingPlayer)
    if depth = 0 or node is a terminal node
        return the heuristic value of node
    if maximizingPlayer
        bestValue := -∞
        for each child of node
            val := minimax(child, depth - 1, FALSE)
            bestValue := max(bestValue, val)
        return bestValue
    else
        bestValue := +∞
        for each child of node
            val := minimax(child, depth - 1, TRUE)
            bestValue := min(bestValue, val)
        return bestValue
	*/
	public int minimax(Chessboard chessboard, int depth, boolean maximizingPlayer) throws CloneNotSupportedException {
		int bestValue, value;
		if (depth == 0 || chessboard.isItCheckMate()) {
			return chessboard.getValue(true);
		}
		bestValue = maximizingPlayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		Piece[] pieces = chessboard.getPieces();
		for (int i = 0; i < pieces.length; i++) {
			int[] movements = pieces[i].getPossibleMovements(chessboard);
			for (int j = 0; j < movements.length; j++) {
				Chessboard clone = this.cloneBoardAndPieces(chessboard);
				pieces[i].move(clone, pieces[i].getPosition(), movements[j]);
				value = minimax(clone, depth - 1, false);
				bestValue = maximizingPlayer ? Math.max(bestValue, value) : Math.min(bestValue, value);
			}
		}
		return bestValue;
	}
	
	private Chessboard cloneBoardAndPieces(Chessboard chessboard) throws CloneNotSupportedException {
		Chessboard cloneBoard = new Chessboard();
		cloneBoard.setBoard(chessboard.cloneBoard());
		Piece[] clonePieces = null;
		clonePieces = chessboard.clonePieces();
		cloneBoard.setPieces(clonePieces);
		return cloneBoard;
	}
	
}
