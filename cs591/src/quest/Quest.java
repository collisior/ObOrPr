package quest;

import java.util.ArrayList;
import java.util.Random;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game implements Color {
	static Random random = new Random();
	static double chanceToMeetMonsters = 0.8;
	QuestBoard board;
	protected Team currentTeam;
	String[] colors = { RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE };
	ArrayList<String> list = new ArrayList<String>();

	final static int TEAM_CAPACITY = 3;

	public void setupTeams() {
		
		System.out.println("How many teams will be playing?");
		int numTeams = 1;
//		int numTeams = InputHandler.getInteger(1, 3);

		for (int i = 0; i < numTeams; i++) {
			Team team = new TeamQuest(i);
//			System.out.printf("How many players will be playing in team %d? \nMax number of players in one team is %d.", team.getName(),
//					TEAM_CAPACITY);
//			int numPlayers = InputHandler.getInteger(1, TEAM_CAPACITY);
			choosePieceFigure(team);
			int numPlayers = 2;
			for (int j = 0; j < numPlayers; j++) {
				Player player = addPlayer();
				team.addPlayer(player);
			}
			SetupQuestHelper.setupTeam(team);
			addTeam(team);
			board.getBoard()[0][0].placePiece(team.getPiece());
		}
	}

	private Piece choosePieceFigure(Team team) {
		System.out.println(team + ", choose playing figure for your team (type #): ");
		char figure = '⬤';
		for (String t : colors) { 
            list.add(t); 
        } 
		String l1 = "";
		String l2 = "";
		for (int i = 0; i < list.size(); i++) {
			l1+=""+i+"   ";
			l2+=""+list.get(i)+figure+RESET +"  ";
		}
		System.out.println(l1 +"\n" + l2);
		int index = InputHandler.getInteger(0, list.size());
		Piece piece = new SimplePiece(figure);
		piece.setColor(list.get(index));
		team.setPiece(piece);
		list.remove(index);
		return piece;
	}

	public void tmpSetupTeams() {
		Team team = new TeamQuest(1);
		Player player = addPlayer();
		Player player2 = addPlayer();
		team.addPlayer(player);
		team.addPlayer(player2);

		SetupQuestHelper.setupTeam(team);
		choosePieceFigure(team);
		addTeam(team);

		board.getBoard()[0][0].placePiece(team.getPiece());
		

	}

	public void startGame() {
		board = new QuestBoard(4, 4);
//		setupTeams();
		tmpSetupTeams(); // by default 1 team, (2 players in 1 team)
		setGamersQueue(getTeams());
		boolean gameStop = false;
		currentTeam = (Team) getNextInQueue(mapTeams);
		board.displayBoard(this);
		while (!gameStop) {
			currentTeam = (Team) getNextInQueue(mapTeams);
			char cellType = teamMove(currentTeam);
			cellTypeHandler(currentTeam, cellType);

			if ((currentTeam.getCurrentRow() == board.rows - 1) && (currentTeam.getCurrentCol() == board.cols - 1)) {
				gameStop = true;
				System.out.println("\nWinners");
			}
		}
	}

	public char teamMove(Team team) {
		char[] acceptedInputs = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i', 'M', 'm' };
		System.out.println(
				team + "'W' - up, 'A'- left, 'S' - down, 'D'-right, 'Q' - quit, 'I'-print info, 'M'- show map. \n");
		char input = Character.toUpperCase(InputHandler.getCharacter(acceptedInputs));
		char cellType = 0;
		switch (input) {
		case 'W':
			System.out.println("Move up >>");
			if (board.isValidMove(team.getCurrentRow() - 1, team.getCurrentCol())) {
				cellType = board.makeMove(team, team.getCurrentRow() - 1, team.getCurrentCol());
			}
			break;
		case 'A':
			System.out.println("Move left >>");
			if (board.isValidMove(team.getCurrentRow(), team.getCurrentCol() - 1)) {
				cellType = board.makeMove(team, team.getCurrentRow(), team.getCurrentCol() - 1);
			}
			break;
		case 'S':
			System.out.println("Move down >>");
			if (board.isValidMove(team.getCurrentRow() + 1, team.getCurrentCol())) {
				cellType = board.makeMove(team, team.getCurrentRow() + 1, team.getCurrentCol());
			}
			break;
		case 'D':
			System.out.println("Move right >>");
			if (board.isValidMove(team.getCurrentRow(), team.getCurrentCol() + 1)) {
				cellType = board.makeMove(team, team.getCurrentRow(), team.getCurrentCol() + 1);
			}
			break;
		case 'I': // team heroes information
			cellType = 'i';
			break;
		case 'M': //show map
			cellType = 'm';
			break;
		case 'Q': //quit
			cellType = 'q';
			System.out.println("Quittting... Bye bye");
			break;
		}
		return cellType;
	}

	/*
	 * Current cell further actions.
	 */
	public void cellTypeHandler(Team team, char cellType) {
		if (cellType == 'M') { // This cell contains monsters -> start fight
			board.displayBoard(this);
			startFight(team);

		} else if (cellType == '$') { // This cell is market -> start shopping/selling, if 'yes'
			for (Player player : team.getTeam()) {
				System.out.println(
						player + ", do you want to enter Market? In Market you may purchase or sell your items.");
				if (InputHandler.YesOrNo()) {
					enterMarket(player);
				}
			}
		} else if (cellType == 'q') { // quit game
			/*
			 * TODO: quitGamehandler
			 */
			System.exit(0);

		} else if (cellType == 'm') {
			board.displayBoard(this);
		} else if (cellType == 'i') {
			for (Player player : team.getTeam()) {
				System.out.println(player + "Information about hero: " + player.getHero().getName());
				player.getHero().information();
				System.out.println("\n");
			}
			System.out.println("Team statistics:");
			System.out.println("Total Fights won:" + ((TeamQuest) team).getTotalFightsWon());
			System.out.println("Total Fights lost:"
					+ (((TeamQuest) team).getTotalFights() - ((TeamQuest) team).getTotalFightsWon()));

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
		System.out.println("team current row col : " + team.getCurrentRow() + ",  " + team.getCurrentCol());
		board.removeMonstersFromCell(team.getCurrentRow(), team.getCurrentCol());
		System.out.println(
				"Fight ended : " + board.getBoard()[team.getCurrentRow()][team.getCurrentCol()].getAllPieces());
		return;
	}

}
