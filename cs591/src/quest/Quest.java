package quest;

import java.util.ArrayList;
import java.util.Random;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color {
	protected static int gameMode = 0; // finish, nofinish
	static Random random = new Random();
	static double chanceToMeetMonsters = 0.8;
	QuestBoard board;
	protected Team currentTeam;
	String[] colors = { RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE };
	ArrayList<String> list = new ArrayList<String>();

	final static int TEAM_CAPACITY = 3;

	public void setupTeams() {
		System.out.println("How many teams will be playing?");
		int numTeams = InputHandler.getInteger(1, colors.length);

		for (int i = 0; i < numTeams; i++) {
			Team team = new TeamQuest(i);
			setupOneTeamQuest(team);
		}
		if (getTeams().size() > 1) {
			System.out.print("\n Do you want to randomize turns between teams?");
			if (InputHandler.YesOrNo()) {
				setGamersQueue(GenericMethods.shuffle(getTeams()));
			} else {
				setGamersQueue(getTeams());
			}
		} else {
			setGamersQueue(getTeams());
		}

	}

	private Piece choosePieceFigure(Team team) {
		System.out.println(team + ", choose playing figure for your team (type #): ");
		char figure = '⬤';

		String l1 = "";
		String l2 = "";
		for (int i = 0; i < list.size(); i++) {
			l1 += "" + i + "   ";
			l2 += "" + list.get(i) + figure + RESET + "  ";
		}
		System.out.println(l1 + "\n" + l2);
		int index = InputHandler.getInteger(0, list.size());
		Piece piece = new SimplePiece(figure);
		piece.setColor(list.get(index));
		team.setPiece(piece);
		list.remove(index);
		return piece;
	}

	public void setupOneTeamQuest(Team team) {
		choosePieceFigure(team);
		System.out.printf(
				"How many players will be playing in team '%s'? \nMax number of players in one team is %d. \n",
				team.getName(), TEAM_CAPACITY);
		int numPlayers = InputHandler.getInteger(1, TEAM_CAPACITY);
		for (int j = 0; j < numPlayers; j++) {
			Player player = addPlayer();
			team.addPlayer(player);
		}
		SetupQuestHelper.setupTeam(team);
		addTeam(team);
		if (gameMode == 0) {
			board.getBoard()[0][0].placePiece(team.getPiece());
		} else {
			board.putInRandomCell(team.getPiece());
		}

	}

	public void startGame() {
		for (String t : colors) {
			list.add(t);
		}
		chooseQuestMode();
		board = new QuestBoard(4, 4);
		setupTeams();
		boolean gameStop = false;
		board.displayBoard(this);
		while (!gameStop) {
			currentTeam = (Team) getNextInQueue(mapTeams);
			System.out.println("\n\n\n Team turn: " + currentTeam + "\n\n");
			teamMove(currentTeam);
			if ((gameMode == 0) && (currentTeam.getCurrentRow() == board.rows - 1)
					&& (currentTeam.getCurrentCol() == board.cols - 1)) {
				gameStop = true;
				System.out.println("\nWinners");
			}
		}
	}

	private void chooseQuestMode() {
		System.out.printf("Choose Quest mode:\n"
				+ "'0' - Destination-based Quest. Your goal will be to reach right-bottom corner cell.\n"
				+ "'1' - Endless Quest. Adventure game with no finish cell. An endless adventure...\n");
		gameMode = InputHandler.getInteger(0, 1);
	}

	public void teamMove(Team team) {
		char[] acceptedInputs = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i', 'M', 'm' };
		System.out.println(team
				+ ", enter your move:\n 'W' - up, 'A'- left, 'S' - down, 'D'-right, 'Q' - quit, 'I'-print info, 'M'- show map. \n");
		boolean teamMoved = false;
		while (!teamMoved) {
			char input = Character.toUpperCase(InputHandler.getCharacter(acceptedInputs));
			char cellType = 0;
			switch (input) {
			case 'W':
//				System.out.println("Move up >>");
				if (board.isValidMove(team.getCurrentRow() - 1, team.getCurrentCol())) {
					cellType = board.makeMove(team, team.getCurrentRow() - 1, team.getCurrentCol());
					teamMoved = true;
				} else {

				}
				break;
			case 'A':
//				System.out.println("Move left >>");
				if (board.isValidMove(team.getCurrentRow(), team.getCurrentCol() - 1)) {
					cellType = board.makeMove(team, team.getCurrentRow(), team.getCurrentCol() - 1);
					teamMoved = true;
				}
				break;
			case 'S':
//				System.out.println("Move down >>");
				if (board.isValidMove(team.getCurrentRow() + 1, team.getCurrentCol())) {
					cellType = board.makeMove(team, team.getCurrentRow() + 1, team.getCurrentCol());
					teamMoved = true;
				}
				break;
			case 'D':
//				System.out.println("Move right >>");
				if (board.isValidMove(team.getCurrentRow(), team.getCurrentCol() + 1)) {
					cellType = board.makeMove(team, team.getCurrentRow(), team.getCurrentCol() + 1);
					teamMoved = true;
				}
				break;
			case 'I': // team heroes information
				cellType = 'i';
				break;
			case 'M': // show map
				cellType = 'm';
				break;
			case 'Q': // quit
				cellType = 'q';
				System.out.println("Quittting... Bye bye");
			}
			cellTypeHandler(currentTeam, cellType);
		}
	}

	/*
	 * Current cell further actions.
	 */
	public void cellTypeHandler(Team team, char cellType) {
		if (cellType == 'M') { // This cell contains monsters -> start fight
			board.displayBoard(this);
			startFight(team);
		} else if (cellType == '$') { // This cell is market -> start shopping/selling, if 'yes'
			board.displayBoard(this);
			for (Player player : team.getTeam()) {
				System.out.println(
						player + ", do you want to enter Market? In Market you may purchase or sell your items.");
				if (InputHandler.YesOrNo()) {
					enterMarket(player);
				}
			}
		} else if (cellType == 'q') { // quit game
			quitGame();

		} else if (cellType == 'm') {
			board.displayBoard(this);
		} else if (cellType == 'i') {
			for (Player player : team.getTeam()) {
				System.out.println(
						player + ". Information about hero: " + player.getHero().color + player.getHero() + RESET);
				player.getHero().information();
				System.out.println("\n");
			}
			String s = BACKGROUND_BLACK + "-----------TEAM OVERALL-------------\n" + "\n\tTotal Fights won: "
					+ ((TeamQuest) team).getTotalFightsWon() + "\n\tTotal Fights lost:"
					+ (((TeamQuest) team).getTotalFights() - ((TeamQuest) team).getTotalFightsWon())
					+ "-------------------------------------\n" + RESET;
			System.out.println(s);
		}
	}

	private void enterMarket(Player player) {
		System.out.println(player + ", do you want sell your items for the half of the price?");
		if (InputHandler.YesOrNo()) {
			Market.sellToMarket(player.getHero());
		}
		System.out.println(player + ", do you want to purchase new ammunition from Market?");
		if (InputHandler.YesOrNo()) {
			Market.purchaseFromMarket(player.getHero());
		}
		System.out.println(player + ", do you want exit Market? (Type 'N' if you want to continue shopping/selling)");
		if (InputHandler.YesOrNo()) {
			// player exits market
		} else {
			enterMarket(player);
		}
	}

	private void startFight(Team team) {
		Fight.fightCountdown();
		Fight.fight(team);
		System.out.println("team current row col : " + team.getCurrentRow() + ",  " + team.getCurrentCol());
		board.removeMonstersFromCell(team.getCurrentRow(), team.getCurrentCol());
		System.out.println(
				"Fight ended : " + board.getBoard()[team.getCurrentRow()][team.getCurrentCol()].getAllPieces());
		return;
	}

}
