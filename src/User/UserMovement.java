package User;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tuukka
 */
public class UserMovement {
	
	/**
	 * Method receives move commands from human user, parses those into coordinates,
	 * check's the validity of the command (coordinates are not outside the board
	 * and the command obeys chess's rules. If the move command is valid, method
	 * updates the char-array and outputs "true" boolean value.
	 * @param command Move command received from the human user, such as "c4e5".
	 * @param chessboard
	 * @return True if command is valid and successful, false otherwise.
	 */
	public static boolean movePiece(String command, Chessboard chessboard) {
		command = command.toUpperCase();
		if (command.length() != 4) {
			return false;
		}
		int start, end;
		start = ((8 - Integer.parseInt(command.substring(1, 2))) * 8) + (int)command.charAt(0) - 65;
		end = ((8 - Integer.parseInt(command.substring(3))) * 8) + (int)command.charAt(2) - 65;
		// Are coordinates inside the board
		if (0 > start || start > 63 || 0 > end || end > 63) {
			return false;
		}
		// Is a piece found at start coordinates
		Piece piece = chessboard.getPiece(start);
		if (piece == null) {
			return false;
		}
		// Is the piece player's own piece (i.e. white) and is the move successful
		if (piece.amIWhite()) {
			return piece.move(chessboard, end);
		} else {
			return false;
		}	
	}
	
}
