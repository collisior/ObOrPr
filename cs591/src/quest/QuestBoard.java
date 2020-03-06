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
	public char makeMove(int prevR, int prevC, int row, int col, Piece teamPiece) {
		this.getBoard()[prevR][prevC].removeLastPiece();
		this.getBoard()[row][col].placePiece(teamPiece);
		if (this.getBoard()[row][col].getSinglePiece().getPieceFigure() == ' ') {
			if (monstersExist()) {
				// fight
				return 'M';
			} else {
				return ' ';
			}
		}
		return this.getBoard()[row][col].getSinglePiece().getPieceFigure();
	}

	public boolean monstersExist() {
		double x = Math.random();
		if (x < Quest.chanceToMeetMonsters) {
			return true;
		}
		return false;
	}

	private void spreadCells() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				board[r][c].placePiece(getRandomCell());
			}
		}
		if (board[0][0].getSinglePiece().getPieceFigure() == 'X') {
			board[0][0].clearCell();
			board[0][0].placePiece(new SimplePiece(' '));
		}
		board[rows - 1][cols - 1].clearCell();
		board[rows - 1][cols - 1].placePiece(new SimplePiece('F'));
		// TODO: check if path exists
	}

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
		String frame = "";
		for (int n = 0; n < cols; n++) {
			frame += "+------";
		}
		frame += "+\n";
		String b = "";
		String tmp = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				tmp = "|" + BLACK + " ` ` ," + ANSI_RESET;
				if (getBoard()[i][j].getSinglePiece().getPieceFigure() == 'X') { // non accessible forest
					tmp = "|" + GREEN + "\\\\\\\\\\\\" + ANSI_RESET;
				} else if (getBoard()[i][j].getSinglePiece().getPieceFigure() == '$') { // market
					tmp = "|" + CYAN + " /" + ANSI_RESET + CYAN + "MM" + ANSI_RESET + CYAN + "\\ " + ANSI_RESET;
				} else if (getBoard()[i][j].getTotalPieces() > 1) {
					if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
						tmp = "|" + PURPLE + "******" + ANSI_RESET;
					}
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
					if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) {
						tmp = "|" + RED + "/'⬤''\\\\" + ANSI_RESET;
					}
				} else if (getBoard()[i][j].getTotalPieces() > 1) {
					if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
						tmp = "|" + PURPLE + "**" + ANSI_RESET + RED + "⬤" + ANSI_RESET + PURPLE + "**" + ANSI_RESET;
					} else {
						tmp = "|" + RED + "  ⬤  " + ANSI_RESET;
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
				} else if (getBoard()[i][j].getTotalPieces() > 1) {
					if (getBoard()[i][j].pieceExists(new SimplePiece('M'))) { // monsters in this cell
						tmp = "|" + PURPLE + "******" + ANSI_RESET;
					}
				}
				b += tmp;
				tmp = "";
			}
			b += "|\n" + frame;
		}
		System.out.println(frame + b);
	}
}
