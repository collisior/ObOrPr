package tictactoe;

/*
 * The Board class models the game board.
 */
public abstract class Board {

	private Cell[][] board;
	private int rows, cols;
	final static int MAX_BOARD_SIDE_DIMENSION = 1000;
	/*
	 * Constructor to initialize empty board (dimensions retrieved from user)
	 */
	public Board() {
		int[] row_col = getBoardDimensionFromUser();
		setRows(row_col[0]);
		setCols(row_col[1]);
		setBoard(new Cell[rows][cols]);
	}

	/*
	 * Constructor to initialize empty board (dimensions are fixed from game)
	 */
	public Board(int rows, int cols) {
		setRows(rows);
		setCols(cols);
		setBoard(new Cell[rows][cols]);
	}

	/*
	 * Return this board
	 */
	public Cell[][] getBoard() {
		return this.board;
	}

	/*
	 * Setup board with n empty cells
	 */
	public void setBoard(Cell[][] board) {
		this.board = board;
		for (int r = 0; r < this.rows; r++) {
			for (int c = 0; c < this.cols; c++) {
				this.getBoard()[r][c] = new Cell(r, c);
			}
		}
	}
	/*
	 * Set board rows dimension.
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/*
	 * Set board columns dimension.
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}
	/*
	 * Return maximum possible board side dimension
	 */
	public int getBoardLimit() {
		return MAX_BOARD_SIDE_DIMENSION;
	}
	/*
	 * Return board side dimensions (rows, columns)
	 */
	public int[] getBoardDimensions() {
		return new int[] { rows, cols };
	}
	/*
	 * Return this board number of rows
	 */
	public int getBoardRows() {
		return rows;
	}

	/*
	 * Return this board number of columns
	 */
	public int getBoardCols() {
		return cols;
	}
	/*
	 * Return this board's larger side dimension
	 */
	public int maxSide() {
		if (rows < cols) {
			return cols;
		}
		return rows;
	}
	/*
	 * Return this board's smaller side dimension
	 */
	public int minSide() {
		if (rows > cols) {
			return cols;
		}
		return rows;
	}
	/*
	 * Return this board number of cells
	 */
	public int getNumberCells() {
		return rows * cols;
	}
	/*
	 * Return true if this board has empty cell(s)
	 */
	public boolean availableCells() {
		for (int r = getBoardRows(); r < getBoardRows(); r++) {
			for (int c = 0; c < getBoardCols(); c++) {
				if (getBoard()[r][c].isEmptyCell()) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Return true if board position is in this Board's range.
	 */
	public boolean boardPositionExists(int row, int col) {
		if ((row < 0) || (row > (rows - 1))) {
			return false;
		}
		if ((col < 0) || (col > (cols - 1))) {
			return false;
		}
		return true;
	}

	/*
	 * Retrieve board dimensions, and handle invalid inputs until valid input is
	 * typed.
	 */
	public int[] getBoardPositionFromUser() {
		int[] row_col = new int[] { -1, -1 };

		boolean playerInputIsValid = false;

		while (playerInputIsValid == false) {

			row_col = InputHandler.getTwoIntegers();
			row_col[0] = row_col[0] - 1;
			row_col[1] = row_col[1] - 1;

			if (boardPositionExists(row_col[0], row_col[1])) {
				playerInputIsValid = true;
			} else {
				System.out.printf(
						"\nGiven (%d, %d) cell position is out of range!"
						+ "\nTry different position in range rows: [1, %d] and cols: [1, %d].\n",
						row_col[0] + 1, row_col[1] + 1, rows, cols);
			}
		}
		return row_col;
	}
	 /* 
	  * Retrieve board dimensions, and handle invalid inputs until valid inputs.
	  */
	public int[] getBoardDimensionFromUser() {
		int[] row_col = new int[] { -1, -1 };
		System.out.printf("Enter board dimensions. Possible dimension range [%d,%d].\n",3,MAX_BOARD_SIDE_DIMENSION);
		System.out.printf("You will need enourmous display to play with sides more than %d cells...\n", MAX_BOARD_SIDE_DIMENSION);
		boolean playerInputIsValid = false;

		while (playerInputIsValid == false) {

			row_col = InputHandler.getTwoIntegers();

			if ((row_col[0] <= MAX_BOARD_SIDE_DIMENSION) && (row_col[1] <= MAX_BOARD_SIDE_DIMENSION ) 
					&& (row_col[0] > 2) && (row_col[1] > 2 )) {
				playerInputIsValid = true;
			} else {
				System.out.printf(
						"\nGiven (%d, %d) cell position is out of range!",
						row_col[0], row_col[1], rows, cols);
			}
		}
		return row_col;
	}

	/*
	 * Print current game-board.
	 */
	public void displayBoard() {
		String frame = "";
		for (int n = 0; n < cols; n++) {
			frame += "+--";
		}
		frame += "+\n";
		String b = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				b += "|" + getBoard()[i][j] + " ";
			}
			b += "|\n" + frame;
		}
		System.out.println(frame + b);
	}

	/*
	 * Reset game-board. Clear all cells.
	 */
	public void resetBoard() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				getBoard()[r][c].clearCell();
			}
		}
	}

	/*
	 * Set player's mark on targeted cell position on the game-board.
	 */
	public void updateBoard(int row, int col, Piece piece) {
		this.getBoard()[row][col].placePiece(piece);
	}

}
