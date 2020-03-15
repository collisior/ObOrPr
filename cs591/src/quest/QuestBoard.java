package quest;

public class QuestBoard extends Board implements Color {
	/*
	 * Constructor to initialize Quest board (dimensions retrieved from user)
	 */
	QuestBoard() {
		super();
		spreadCells();
	}

	/*
	 * Constructor to initialize fixed ROWxCOL board
	 */
	public QuestBoard(int x, int y) {
		super(x, y);
		spreadCells();
	}

	public void enterMarket() {

	}

	public boolean isValidMove(int r, int c) {
		if (boardPositionExists(r, c)) {
			if (board[r][c].getSinglePiece().getPieceFigure() == 'X') {
				System.out.println("You shall not pass into this cell! Try another move.");
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	/*
	 * Set player's mark on targeted cell position on the game-board.
	 */
	char makeMove(Team team, int row, int col) {
		this.getBoard()[team.getCurrentRow()][team.getCurrentCol()].removePiece(team.getPiece());
		putTeamInCell(team, row, col);
		System.out.println("this.getBoard()[row][col] All() - "+ this.getBoard()[row][col].getAllPieces());
		if ((this.getBoard()[row][col].getSinglePiece().getPieceFigure() == ' ') &&(this.getBoard()[row][col].getSinglePiece().getPieceFigure() != '$')) {
			if (monstersExist()) {
				board[row][col].placePiece(new SimplePiece('M')); // put monster piece
				System.out.println("this.getBoard()[row][col] All() - "+ this.getBoard()[row][col].getAllPieces());
				return 'M';
			}
		}
		System.out.println("this.getBoard()[row][col].getSinglePiece().getPieceFigure() - "+ this.getBoard()[row][col].getSinglePiece().getPieceFigure());
		return this.getBoard()[row][col].getSinglePiece().getPieceFigure();
	}
	/*
	 * Return true if there are monsters in this Cell. Otherwise, if common cell return false.
	 */
	public boolean monstersExist() {
		double x = Math.random();
		if (x < Quest.chanceToMeetMonsters) {
			return true;
		}
		return false;
	}
	
	public void setCommonCell(int row, int col) {
		board[row][col].clearCell();
		board[row][col].placePiece(new SimplePiece(' '));
	}
	
	public void putTeamInCell(Team team, int row, int col) {
		board[row][col].placePiece(new SimplePiece(team.getPiece().getPieceFigure()));
		team.setCurrentRow(row);
		team.setCurrentCol(col);
	}
	public void putMonstersInCell(int row, int col) {
		board[row][col].placePiece(new SimplePiece('M'));
	}
	public void removeMonstersFromCell(int row, int col) {
		board[row][col].removePiece(new SimplePiece('M'));
	}
	private void spreadCells() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				board[r][c].clearCell();
				board[r][c].placePiece(getRandomCell());
			}
		}
		if (board[0][0].getSinglePiece().getPieceFigure() != ' ') {
			board[0][0].clearCell();
			board[0][0].placePiece(new SimplePiece(' '));
		}
		board[rows - 1][cols - 1].clearCell();
		board[rows - 1][cols - 1].placePiece(new SimplePiece('F'));
		// TODO: check if path exists
	}
	/*
	 * Generate random piece, return this piece.
	 */
	private Piece getRandomCell() {
		double x = Math.random();
		Piece p;
		if (x < 0.2) {
			p = new SimplePiece('X'); // non-accessible tile
		} else if (x < 0.5) {
			p = new SimplePiece('$'); // market tile
		} else {
			p = new SimplePiece(' '); // common tile - monster tile
		}
		return p;
	}
	/*
	 * Print current game-board.
	 */
	public void displayBoard() {
//		for (int i = 0; i < rows; i++) {
//			for (int j = 0; j < cols; j++) {
//				System.out.print(getBoard()[i][j].getSinglePiece().getPieceFigure());
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
			System.out.print("\n" );
			for (int j = 0; j < cols; j++) {
				System.out.print(". " + getBoard()[i][j].getAllPieces()+". ");
				tmp = "|" + BLACK + " ` ` ," + ANSI_RESET;
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'X') { // non accessible forest
					tmp = "|" + GREEN + "\\\\\\\\\\\\" + ANSI_RESET;
				} else if (getBoard()[i][j].getSinglePiece().getPieceFigure() == '$') { // market
					tmp = "|" + CYAN + " /" + ANSI_RESET + CYAN + "MM" + ANSI_RESET + CYAN + "\\ " + ANSI_RESET;
				} else if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
					tmp = "|" + PURPLE + "******" + ANSI_RESET;
				}
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'F') { //finish
					tmp = "|" + WHITE + "======" + ANSI_RESET;
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n";
			for (int j = 0; j < cols; j++) {
				tmp = "|" + BLACK + " ,`  `" + ANSI_RESET;
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'X') {
					tmp = "|" + GREEN + "//////" + ANSI_RESET;
				} else if (getBoard()[i][j].getSinglePiece().getPieceFigure() == '$') { // market
					tmp = "|" + CYAN + "/''''\\" + ANSI_RESET;
					if(getBoard()[i][j].pieceExists(new SimplePiece('T'))) { // team in this cell
						tmp = "|" + CYAN + "/" + ANSI_RESET+ RED + " ⬤ " + ANSI_RESET + CYAN + "\\"+ ANSI_RESET;
					}
				} else if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
						tmp = "|" + PURPLE + "**" + ANSI_RESET + RED + "**" + ANSI_RESET + PURPLE + "**" + ANSI_RESET;
						 if (getBoard()[i][j].pieceExists(new SimplePiece('T'))) {
							 tmp = "|" + PURPLE + "**" + ANSI_RESET + RED + "⬤" + ANSI_RESET + PURPLE + "**" + ANSI_RESET;
						 }
				} else  if (getBoard()[i][j].pieceExists(new SimplePiece('T'))) {
					tmp = "|" + RED + "  ⬤  " + ANSI_RESET;
				}
				
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'F') {
					if (getBoard()[i][j].pieceExists(new SimplePiece('T'))) { // finish
						tmp = "|" + RED + "> ⬤ <"+ ANSI_RESET;
					}
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n";
			for (int j = 0; j < cols; j++) {
				tmp = "|" + BLACK + " ,`  `" + ANSI_RESET;
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'X') {
					tmp = "|" + GREEN + "\\\\\\\\\\\\" + ANSI_RESET;
				} else if (getBoard()[i][j].getSinglePiece().getPieceFigure() == '$') { // market
					tmp = "|" + CYAN + "L◻◻◻◻⅃" + ANSI_RESET;
				} else if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
					tmp = "|" + PURPLE + "******" + ANSI_RESET;
				}
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'F') { // finish
					tmp = "|" + WHITE + "======" + ANSI_RESET;
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n" + frame;
		}
		System.out.println(frame + b);
	}
}
