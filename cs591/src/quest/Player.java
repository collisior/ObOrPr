package quest;

/*
* The Player class models Player.
*/

public class Player {

	private Piece piece; // e.g. can be used in TicTacToe, Checkers
	boolean WinnerStatus;
	private int totalWins;
	private int entered_row = -1, entered_col = -1;
	private int id = -1;
	private int teamId;
	private HumanInfo info;
	private QuestCharacter hero;

	// Constructor to initialize this Player
	public Player(int id) {
		this.WinnerStatus = false;
		this.totalWins = 0;
		setId(id);
		System.out.print("Player " + getId() + ": ");
		this.info = new HumanInfo();
	}

	public void setId(int id) {
		this.id = id;
	}
	public HumanInfo getInfo() {
		return info;
	}

	public int getId() {
		return this.id;
	}

	/*
	 * Retrieve this Player's figure
	 */
	public Piece getPlayerPiece() {
		return this.piece;
	}

	/*
	 * Set this Player's piece
	 */
	public void setPlayerPiece(Piece p) {
		this.piece = p;
	}

	/*
	 * Set this Player's team id
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	/*
	 * Clear this Player's team id
	 */
	public void clearTeamId() {
		setTeamId(-1);
	}

	/*
	 * Retrieve this Player's team id
	 */
	public int getTeamId() {
		return this.teamId;
	}

	/*
	 * Change this Player's state to Winner
	 */
	public void changeToWinner() {
		if (this.WinnerStatus == false) {
			this.WinnerStatus = true;
		}
	}

	/*
	 * Change this Player's state to Non Winner
	 */
	public void changeToNonWinner() {
		if (this.WinnerStatus == true) {
			this.WinnerStatus = false;
		}
	}

	/*
	 * Update Player's total number of wins
	 */
	public void addWin() {
		this.totalWins++;
	}

	// Check if this Player is a Winner (return true if Winner)
	public boolean isWinner() {
		return this.WinnerStatus;
	}

	public int getTotalWins() {
		return this.totalWins;
	}

	public int getEnteredRow() {
		return this.entered_row;
	}

	public int getEnteredCol() {
		return this.entered_col;
	}

	public void setEnteredRow(int row) {
		this.entered_row = row;
	}

	public void setEnteredCol(int col) {
		this.entered_col = col;
	}

	public boolean isHuman() {
//		if ()
		return false;
	}

	public String toString() {
		if (this.info.getUsername() != "") {
			return "Player " + getId() + ": " + info.getUsername();
		}
		return "Player " + getId() + ": " + info.getFirstName();
	}

	public QuestCharacter getHero() {
		return hero;
	}

	public void setHero(QuestCharacter hero) {
		this.hero = hero;
	}
}
