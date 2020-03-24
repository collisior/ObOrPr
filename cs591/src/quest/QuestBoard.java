package quest;

public class QuestBoard extends Board implements Color {
	/*
	 * Constructor to initialize Quest board (dimensions retrieved from user)
	 */
	QuestBoard() {
		
		super();
		System.out.println("QuestBoard");
		spreadCells();
	}

	/*
	 * Constructor to initialize fixed ROWxCOL board
	 */
	public QuestBoard(int x, int y) {
		super(x, y);
		spreadCells();
	}

	public boolean isValidMove(int r, int c) {
		if (boardPositionExists(r, c)) {
			if (board[r][c].getSinglePiece().getPieceFigure() == 'X') {
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
				System.out.println(RED + "   You shall not pass into this cell! Try another move." + RESET);
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
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
		if ((this.getBoard()[row][col].getSinglePiece().getPieceFigure() == ' ')
				&& (this.getBoard()[row][col].getSinglePiece().getPieceFigure() != '$')) {
			if (monstersExist()) {
				board[row][col].placePiece(new SimplePiece('M')); // put monster piece
				return 'M';
			}
		}
		return this.getBoard()[row][col].getSinglePiece().getPieceFigure();
	}

	/*
	 * Return true if there are monsters in this Cell. Otherwise, if common cell
	 * return false.
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
		System.out.println("QuestBoard2");
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

		if (Quest.gameMode == 0) { // with finish on right-bottom corner
			board[rows - 1][cols - 1].clearCell();
			board[rows - 1][cols - 1].placePiece(new SimplePiece('F'));
		}
		/*
		 * Check if path exists from from top left cell to bottom right cell. Call
		 * spreadcells() again if path not found.
		 */
		if (!validPathExist()) {
			spreadCells();
		}
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

	public void displayBoard(Game game) {
		if (game instanceof Quest) {
			DisplayBoard.showQuestBoard((Quest) game, this);
		}
	}

	public boolean validPathExist() {
		int matrix[][] = new int[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (board[r][c].getSinglePiece().getPieceFigure() == 'X') {
					matrix[r][c] = -1; // blocked cell
				} else {
					matrix[r][c] = 0; // accessible cell
				}
			}
		}
		matrix[0][0] = 0; // source
		matrix[rows - 1][cols - 1] = 0; // destination

		if (GFG.isPath(matrix)) {
			return true;
		}
		return false;
	}

	public void putInRandomCell(Piece piece) {
		boolean foundAvailableCell = false;
		while (!foundAvailableCell) {
			int row = Quest.random.nextInt(rows - 1);
			int col = Quest.random.nextInt(cols - 1);
			if (board[row][col].getSinglePiece().getPieceFigure() != 'X') {
				board[row][col].placePiece(piece);
				foundAvailableCell = true;
			}
		}
	}
}
