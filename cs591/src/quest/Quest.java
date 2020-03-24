package quest;

import java.util.ArrayList;
import java.util.Random;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color, Vizualization {
	protected static int gameMode = 0; // finish, endless, reach level
	static Random random = new Random();
	static double chanceToMeetMonsters = 0.8;
	static int levelMascot = 3; // set level number when Hero will receive his Mascot
	static int levelEnd = 10; // level number to indicate winners (reach-level quest mode)
	QuestBoard board;
	protected TeamQuest currentTeam;
	String[] colors = { RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE };
	ArrayList<String> list = new ArrayList<String>();

	final static int TEAM_CAPACITY = 3;

	public void setupTeams() {
		System.out.println("\nHow many teams will be playing?");
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
		char figure = '@';

		String l1 = "";
		String l2 = "";
		for (int i = 0; i < list.size(); i++) {
			l1 += "" + i + "  ";
			l2 += "" + list.get(i) + figure + RESET + "  ";
		}
		System.out.println(l1 + "\n" + l2);
		int index = InputHandler.getInteger(0, list.size());
		Piece piece = new SimplePiece(figure);
		piece.setColor(list.get(index));
		team.color = list.get(index);
		team.setPiece(piece);
		list.remove(index);
		return piece;
	}

	public void setupOneTeamQuest(Team team) {
		choosePieceFigure(team);
		System.out.printf(
				"\nHow many players will be playing in team '%s'? \nMax number of players in one team is %d. \n",
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
		board = new QuestBoard();
//		board.displayBoard(this);
		setupTeams();
		boolean gameStop = false;
		currentTeam = (TeamQuest) getNextInQueue(mapTeams);
		board.displayBoard(this);
		while (!gameStop) {
			teamMove(currentTeam);
			if (questEnd()) {
				gameStop = questEndHandler();
			}
			currentTeam = (TeamQuest) getNextInQueue(mapTeams);
		}
	}

	public void playAgain() {
		chooseQuestMode();
		board = new QuestBoard();
		for (int teamId : getTeams()) {
			Team team = getTeam(teamId);
			((TeamQuest) team).resetTeam();
			if (gameMode == 0) {
				board.getBoard()[0][0].placePiece(team.getPiece());
			} else {
				board.putInRandomCell(team.getPiece());
			}
		}
		boolean gameStop = false;
		currentTeam = (TeamQuest) getNextInQueue(mapTeams);
		board.displayBoard(this);
		while (!gameStop) {
			teamMove(currentTeam);
			if (questEnd()) {
				gameStop = questEndHandler();
			}
			currentTeam = (TeamQuest) getNextInQueue(mapTeams);
		}
	}

	private boolean questEnd() {
		if ((gameMode == 0) && (currentTeam.getCurrentRow() == board.rows - 1)
				&& (currentTeam.getCurrentCol() == board.cols - 1)) {
			return true;
		} else if (gameMode == 1) {
			// do nothing
		} else if (gameMode == 2) {
			if (currentTeam.allTeamReachedLevel(levelEnd)) {
				return true;
			}
		}
		return false;
	}

	private boolean questEndHandler() {
		String yellowLine = YELLOW + "````````````````````````````````````````````" + RESET;

		if (gameMode == 0) {
			System.out.println(yellowLine + yellowLine + "\n \tCongratulations!!! \n\t" + currentTeam
					+ " reached finish Cell! \n" + yellowLine + yellowLine);
		} else if (gameMode == 2) {
			System.out.println(yellowLine + yellowLine + "\n \tCongratulations!!! \n\t All member of " + currentTeam
					+ " reached level 10! \n" + yellowLine + yellowLine);
		}
		System.out.println("\n[0] - exit!" + "\n[1] - exit to Welcome page. (Game history will be erased!)"
				+ "\n[2] - play again with the same team setup (you may pick different Quest mode and change Heroes, but your Ammunition strorage will be saved).");
		int answer = InputHandler.getInteger(0, 2);
		if (answer == 0) {
			quitGame();
		} else if (answer == 1) {
			System.out.println("\nYou decided to go to welcome page!\n");
			Main.main(null);
		} else if (answer == 2) {
			playAgain();
		}
		return false;
	}
	private void chooseQuestMode() {
		System.out.printf("Choose Quest mode:\n"
				+ "'0' - Destination-based: Your goal will be to reach right-bottom corner cell.\n"
				+ "'1' - Endless: An endless adventure...\n"
				+ "'2' - Reach-level-10: Team wins if all its members reach level 10.\n");
		gameMode = InputHandler.getInteger(0, 2);
		System.out.println(BOARD_CELLS_INFO);
	}

	public void teamMove(Team team) {
		char[] acceptedInputs = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i', 'M', 'm' };
		boolean teamMoved = false;
		while (!teamMoved) {
			System.out.println(team
					+ ", enter your move:\n 'W' - up, 'A'- left, 'S' - down, 'D'-right, 'Q' - quit, 'I'-print info, 'M'- show map. \n");
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
				System.out.println(player);
				player.getHero().information();
				System.out.println("");
			}
			String s = "-----------TEAM OVERALL-------------\n" + "\tTotal Fights won: "
					+ ((TeamQuest) team).getFightsWon() + "\n\tTotal Fights lost:" + ((TeamQuest) team).getFightsLost()
					+ "\n-------------------------------------\n";
			System.out.println(s);
		} else {
			board.displayBoard(this);
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
		board.removeMonstersFromCell(team.getCurrentRow(), team.getCurrentCol());
		return;
	}

}
