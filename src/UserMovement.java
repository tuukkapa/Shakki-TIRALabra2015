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
		int startCol, startRow, endCol, endRow;
		startCol = (int)command.charAt(0) - 65;
		startRow = 8 - Integer.parseInt(command.substring(1, 2));
		endCol = (int)command.charAt(2) - 65;
		endRow = 8 - Integer.parseInt(command.substring(3));
		boolean error = startCol < 0 || startCol > 7 ||
				startRow < 0 || startRow > 7 ||
				endCol < 0 || endCol > 7 ||
				endRow < 0 || endRow > 7;
		if (error) {
			return false;
		}
		char[][] tempboard = chessboard.getBoard();
		switch(tempboard[startRow][startCol]) {
			case 'P': movedone = chessboard.movePawn(true, startRow, startCol, endRow, endCol);
				break;
			case 'R': movedone = chessboard.moveRook(true, startRow, startCol, endRow, endCol);
				break;
			case 'N': movedone = chessboard.moveKnight(true, startRow, startCol, endRow, endCol);
				break;
			case 'B': movedone = chessboard.moveBishop(true, startRow, startCol, endRow, endCol);
				break;
			case 'Q': movedone = chessboard.moveQueen(true, startRow, startCol, endRow, endCol);
				break;
			case 'K': movedone = chessboard.moveKing(true, startRow, startCol, endRow, endCol);
				break;
			default: movedone = false;
				break;
		}
		if (!movedone) {
			return false;
		}
		return true;
	}
	
}
