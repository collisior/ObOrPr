package quest;

import java.util.ArrayList;

public class DisplayBoard implements Color {
	/*
	 * Display this quest game-board at current state.
	 */
	public static void showQuestBoard(Quest quest, QuestBoard board) {
//		 new QuestBoard(8,9); 
		int cols = board.cols;
		int rows = board.rows;
		quest.getTeams();
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				System.out.print( board.board[i][j].getSinglePiece().getPieceFigure());
//			}
//			System.out.println();
//		}
		String frame = "";
		for (int n = 0; n < cols; n++) {
			frame += "+------";
		}
		frame += "+\n";
		String b = "";
		String tmp = "";
		for (int i = 0; i < rows; i++) {
			System.out.print("\n");
			for (int j = 0; j < cols; j++) {
				/*
				 * Use below line to print all pieces in cells...
				 */
				System.out.print(". " + board.board[i][j].getAllPieces() + " ");
				//
				tmp = "|" + BLACK + " ` ` ," + RESET;

				if (board.board[i][j].getSinglePiece().getPieceFigure() == 'X') { // non accessible forest
					tmp = "|" + GREEN + "\\\\\\\\\\\\" + RESET;
				} else if (board.board[i][j].getSinglePiece().getPieceFigure() == '$') { // Magic market
					tmp = "|" + CYAN + " /" + RESET + CYAN + "MM" + RESET + CYAN + "\\ " + RESET;
				} else if (board.board[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
					tmp = "|" + PURPLE + "******" + RESET;
				}
				if ((Quest.gameMode == 0) &&  (board.board[i][j].getSinglePiece().getPieceFigure() == 'F')) { // finish
					tmp = "|" + WHITE + "======" + RESET;
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n";
			for (int j = 0; j < cols; j++) {
				
				tmp = "|" + BLACK + " ,`  `" + RESET;
				if (board.board[i][j].getSinglePiece().getPieceFigure() == 'X') {// non accessible forest
					tmp = "|" + GREEN + "//////" + RESET;
				} 
				else if (board.board[i][j].getSinglePiece().getPieceFigure() == '$') { // Magic market
					tmp = "|" + CYAN + "/''''\\" + RESET;
					if (currentTeamHere(i, j, quest)) {
						tmp = "|" + CYAN + "/" + RESET +RED+">"+RESET+ quest.currentTeam.getPiece() +RED+"<"+RESET+ CYAN + "\\" + RESET;
					} else if (teamExist(i, j, quest)) {
						tmp = "|" + CYAN + "/ " + RESET + getPiece(i, j, quest) + CYAN + " \\" + RESET;
					}
				} 
				else if (board.board[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
					tmp = "|" + PURPLE + "**" + RESET + RED + "**" + RESET + PURPLE + "**" + RESET;
					if (currentTeamHere(i, j, quest)) {
						tmp = "|" + PURPLE + "*" + RESET +RED+">"+RESET+ quest.currentTeam.getPiece() +RED+"<"+RESET+PURPLE + "*" + RESET;
					} else if (teamExist(i, j, quest)) {
						tmp = "|" + PURPLE + "**" + RESET + getPiece(i, j, quest) + PURPLE + "**" + RESET;
					}
				} else if (teamExist(i, j, quest)) {
					tmp = "|  " + getPiece(i, j, quest) + "  ";
					if (currentTeamHere(i, j, quest)) {
						tmp = "| "+RED+">"+RESET + quest.currentTeam.getPiece() +RED+"<"+RESET+ " ";
					}					
				}
				// if played with FINISH cell 
				if ((Quest.gameMode == 0) && (board.board[i][j].getSinglePiece().getPieceFigure() == 'F')) {
					if (board.board[i][j].pieceExists(new SimplePiece('⬤'))) { // finish
						tmp = "|> "+getPiece(i, j, quest)+" <";
					}
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n";
			for (int j = 0; j < cols; j++) {
				tmp = "|" + BLACK + " ,`  `" + RESET;
				if (board.board[i][j].getSinglePiece().getPieceFigure() == 'X') {// non accessible forest
					tmp = "|" + GREEN + "\\\\\\\\\\\\" + RESET;
				} else if (board.board[i][j].getSinglePiece().getPieceFigure() == '$') { // market
					tmp = "|" + CYAN + "L◻◻◻◻⅃" + RESET;
				} else if (board.board[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
					tmp = "|" + PURPLE + "******" + RESET;
				}
				if ((Quest.gameMode == 0) && (board.board[i][j].getSinglePiece().getPieceFigure() == 'F')) { // finish
					tmp = "|" + WHITE + "======" + RESET;
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n" + frame;
		}
		System.out.println("\n" + frame + b);
	}

	/*
	 * Return true if in given cell Team(s) exists.
	 */
	private static boolean teamExist(int row, int col, Quest quest) {
		ArrayList<Integer> listTeams = quest.getTeams();
		for (int id : listTeams) {
			Team team = quest.getTeam(id);
			if ((team.getCurrentCol() == col) && (team.getCurrentRow() == row)) {
				return true;
			}
		}
		return false;
	}
	/*
	 * Check if current team is in this cell
	 */
	private static boolean currentTeamHere(int row, int col, Quest quest) {
		Team team = quest.currentTeam;
		if ((team.getCurrentCol() == col) && (team.getCurrentRow() == row)) {
			return true;
		}
		return false;
	}

	/*
	 * Retrieve Team's Piece in given cell. If more than one team in the same cell
	 * return black circle.
	 */
	private static Piece getPiece(int row, int col, Quest quest) {
		int counter = 0;
		Piece piece = null;
		ArrayList<Integer> listTeams = quest.getTeams();
		for (int id : listTeams) {
			Team team = quest.getTeam(id);
			if ((team.getCurrentCol() == col) && (team.getCurrentRow() == row)) {
				piece = team.getPiece();
				counter++;
			}
		}
		if (counter > 1) {
			piece = new SimplePiece('⬤');
			piece.setColor(BLACK);
		}
		return piece;
	}
}
