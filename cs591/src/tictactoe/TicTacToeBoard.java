package tictactoe;

/*
* The TicTacToeBoard class extends Board class. 
* Models and handles TicTacToe board structure and allowences.
*/
public class TicTacToeBoard extends Board {

	private int strike = 3; // by default strike = 3
	private int max_up, max_down, max_right, max_left;
	/*
	 * Constructor to initialize empty TTT board (dimensions retrieved from user)
	 */
	public TicTacToeBoard() {
		super();
		askStrikeNumber();
	}
	/*
	 * Constructor to initialize empty TTT board (dimensions are given, strike number = 3 by default)
	 */
	public TicTacToeBoard(int x, int y) {
		super(x, y);
	}
	/*
	 * Constructor to initialize empty TTT board (dimensions and strike number are given)
	 */
	public TicTacToeBoard(int x, int y, int strike) {
		super(x, y);
		setStrikeNumber(strike);
	}
	/*
	 * Retrieve from user the strike number (based on the board dimensions they
	 * chose).
	 */
	public void askStrikeNumber() {
		System.out.println("How big will be winning strike number for this TicTacToe game?");
		setStrikeNumber(InputHandler.getInteger(2, minSide()));
	}
	/* 
	 * Set  strike number for this TTT game
	 */
	public void setStrikeNumber(int strike) {
		this.strike = strike;
	}
	/*
	 * Return strike number to win the game
	 */
	public int getStrikeNumber() {
		return this.strike;
	}

	/*
	 * Return true if player's move resulted in victory. If true update player's
	 * total wins.
	 */
	public boolean checkWin(Player player) {
		Piece p = player.getPlayerPiece();
		setStrikeSearchBorders(player.getEnteredRow(), player.getEnteredCol());
		
		if (checkStrikeHorizontal(player.getEnteredRow(), player.getEnteredCol(), p.getPieceFigure())
				|| checkStrikeVertical(player.getEnteredRow(), player.getEnteredCol(), p.getPieceFigure())
				|| checkStrikeDiagonalPositive(player.getEnteredRow(), player.getEnteredCol(), p.getPieceFigure())
				|| checkStrikeDiagonalNegative(player.getEnteredRow(), player.getEnteredCol(), p.getPieceFigure())) {
			return true;
		}
		return false;
	}

	/*
	 * Return true if player's move resulted in victory horizontally
	 */
	public boolean checkStrikeHorizontal(int row, int col, char player_mark) {
//		System.out.println("Checks horizontal stuff ...");
		int counter= 0 ;
		for (int left = col; left >= max_left; left--) {
			if (getBoard()[row][left].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[row][left].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
				} else {
					break;
				}
			}
		}
		counter--;
		for (int right = col; right <= max_right; right++) {
			if (getBoard()[row][right].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[row][right].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
				} else {
					break;
				}
			}
		}
		return false;
	}

	/*
	 * Return true if player's move resulted in victory vertically
	 */
	public boolean checkStrikeVertical(int row, int col, char player_mark) {
//		System.out.println("Checks vertical stuff ...");
		int counter = 0;
		
		for (int up = row; up >= max_up; up--) {
			if (getBoard()[up][col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[up][col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
				} else {
					break;
				}
			}
		}
		counter--;
		for (int down = row; down <= max_down; down++) {
			if (getBoard()[down][col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[down][col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
				} else {
					break;
				}
			}

		}
		return false;
	}

	/*
	 * Return true if player's move resulted in victory positive Diagonally e.g.`/`
	 */
	
	public boolean checkStrikeDiagonalPositive(int row, int col, char player_mark) {
//		System.out.println("Checks diagonal+ stuff ...");
		int counter = 0;
		int var_col = col;

		for (int r = row; r <= max_down; r++) {
			if (getBoard()[r][var_col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[r][var_col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
					var_col--;
				} else {
					break;
				}
			}
			if (var_col < 0) {
				break;
			}

		}
		counter--;
		var_col = col;
		for (int r = row; r >= max_up; r--) {
			if (getBoard()[r][var_col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[r][var_col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
					var_col++;
				} else {
					break;
				}
			}
			if (var_col > max_right) {
				break;
			}
		}
		return false;
	}

	/*
	 * Return true if player's move resulted in victory negative Diagonally e.g.`\`
	 */
	public boolean checkStrikeDiagonalNegative(int row, int col, char player_mark) {
//		System.out.println("Checks diagonal- stuff ...");
		int counter = 0;
		int var_col = col;

		for (int r = row; r >= max_up; r--) {
			if (getBoard()[r][var_col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[r][var_col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
					var_col--;
				} else {
					break;
				}
			}
			if (var_col < 0) {
				break;
			}
		}
		counter--;
		var_col = col;
		for (int r = row; r <= max_down; r++) {
			if (getBoard()[r][var_col].isEmptyCell()) {
				break;
			} else {
				if (getBoard()[r][var_col].getSinglePiece().getPieceFigure() == player_mark) {
					counter++;
					if (counter == strike) {
						return true;
					}
					var_col++;
				} else {
					break;
				}
			}
			if (var_col > max_right) {
				break;
			}
		}
		return false;
	}

	/*
	 * Return true if all Board cells are occupied and both players are not winners
	 */
	public boolean checkDraw() {
		
		return false;
	}
	/*
	 * Set possible strike borders from the given cell position
	 */
	public void setStrikeSearchBorders(int row, int col) {
		max_left = col - strike + 1;
		max_down = row + strike - 1;
		max_right = col + strike - 1;
		max_up = row - strike + 1;

		if (max_up < 0) {
			max_up = 0;
		}
		if (max_down >= getBoardRows()) {
			max_down = getBoardRows() - 1;
		}
		if (max_left < 0) {
			max_left = 0;
		}
		if (max_right >= getBoardCols()) {
			max_right = getBoardCols() - 1;
		}
	}

	/*
	 * Retrieve player's move position. Handle position inputs from this Player.
	 * Update board if valid move position. Check if player won.
	 */
	public void makeMove(Player p) {
		System.out.print(p + ", type board position. \n");

		boolean playerInputIsValid = false;

		while (playerInputIsValid == false) {

			int[] row_col = getBoardPositionFromUser();

			// if this cell is available put Player's piece
			if (getBoard()[row_col[0]][row_col[1]].isEmptyCell()) {

				p.setEnteredRow(row_col[0]);
				p.setEnteredCol(row_col[1]);
				Piece piece = new SimplePiece(p.getPlayerPiece().getPieceFigure());
				
				updateBoard(row_col[0], row_col[1], piece);

				piece.setXY(row_col[0], row_col[1]);
				if (checkWin(p)) {
					p.changeToWinner();
					p.addWin();
				}
				
				playerInputIsValid = true;
			} else {
				displayBoard();
				System.out.println("This board position is occupied! Try another position...");
			}

		}
	}
}
