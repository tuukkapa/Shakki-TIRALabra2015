package User;


import Chessboard.Chessboard;
import Chessboard.pieces.Piece;

/**
 * Static class with a method receiving user's movement commands.
 * @author Tuukka Paukkunen <tuukka.paukkunen@cs.helsinki.fi>
 */
public class UserMovement {
	
	/**
	 * Method receives move commands from human user, parses those into coordinates,
	 * and relays the command to the chessboard, and outputs "true" boolean value.
	 * @param command String, move command received from the human user, such as "c4e5".
	 * @param chessboard Chessboard-object, which pieces are to be moved.
	 * @return True if command is valid and successful, false otherwise.
	 */
	public static boolean movePiece(String command, Chessboard chessboard) {
		command = command.toUpperCase();
		if (command.length() != 4) {
			return false;
		}
		int start = ((8 - Integer.parseInt(command.substring(1, 2))) * 8) + (int)command.charAt(0) - 65;
		int end = ((8 - Integer.parseInt(command.substring(3))) * 8) + (int)command.charAt(2) - 65;
		// Are the coordinates inside the board
		if (0 > start || start > 63 || 0 > end || end > 63) {
			return false;
		}
		// Is a piece found at the starting coordinates
		Piece piece = chessboard.getPiece(start);
		if (piece == null) {
			return false;
		}
		// Is the piece player's own piece (i.e. white) and is the move successful
		if (piece.amIWhite()) {
			return chessboard.movePiece(start, end);
		} else {
			return false;
		}	
	}
	
}
