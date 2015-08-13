
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
	 * @param Chessboard Reference to the Chessboard-object.
	 * @return True if command is valid and successful, false otherwise.
	 */
	public static boolean movePiece(String command, Chessboard chessboard) {
		boolean movedone;
		command = command.toUpperCase();
		if (command.length() != 4) {
			return false;
		}
		int start, end;
		start = (((int)command.charAt(0) - 65 )* 8) + (8 - Integer.parseInt(command.substring(1, 2)));
		end = ((int)command.charAt(2) - 65) + (8 - Integer.parseInt(command.substring(3)));
		//startCol = (int)command.charAt(0) - 65;
		//startRow = 8 - Integer.parseInt(command.substring(1, 2));
		//endCol = (int)command.charAt(2) - 65;
		//endRow = 8 - Integer.parseInt(command.substring(3));
		if (0 > start && start > 63 && 0 > end && end > 63) {
			return false;
		}
		Piece piece = chessboard.getUserPiece(start);
		if (piece == null) {
			return false;
		}
		char[][] tempboard = chessboard.getBoard();
		if (Character.isLowerCase(tempboard[start/8][start%8])) {
			return false;
		}
		switch(tempboard[start/8][start%8]) {
			case 'P': movedone = piece.move(chessboard, end);
				break;/*
			case 'R': movedone = chessboard.moveRook(startRow, startCol, endRow, endCol);
				break;
			case 'N': movedone = chessboard.moveKnight(startRow, startCol, endRow, endCol);
				break;
			case 'B': movedone = chessboard.moveBishop(startRow, startCol, endRow, endCol);
				break;
			case 'Q': movedone = chessboard.moveQueen(startRow, startCol, endRow, endCol);
				break;
			case 'K': movedone = chessboard.moveKing(startRow, startCol, endRow, endCol);
				break;*/
			default: movedone = false;
				break;
		}
		if (!movedone) {
			return false;
		}
		return true;
	}
	
}
