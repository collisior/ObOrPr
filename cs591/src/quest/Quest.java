package quest;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game {

	static double chanceToMeetMonsters = 0.8;
	QuestBoard board;
	
	final static int TEAM_CAPACITY = 3;

	public void setupTeams() {
		System.out.println("How many teams will be playing?");
		int numTeams = 1;
//		int numTeams = InputHandler.getInteger(1, 3);

		for (int i = 0; i < numTeams; i++) {
			Team team = new Team(i);
//			System.out.printf("How many players will be playing in team %d? \nMax number of players in one team is %d.", team.getName(),
//					TEAM_CAPACITY);
//			int numPlayers = InputHandler.getInteger(1, TEAM_CAPACITY);
			team.setPiece(new SimplePiece('T'));
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
	
	public void tmpSetupTeams() {
		Team team = new Team(1);
		Player player = addPlayer();
		Player player2 = addPlayer();
		team.addPlayer(player);
		team.addPlayer(player2);
		
		SetupQuestHelper.setupTeam(team);
		team.setPiece(new SimplePiece('T'));
		addTeam(team);
		
		board.getBoard()[0][0].placePiece(team.getPiece());
		System.out.println("\nTeam piece = " +team.getPiece()+ "\n");
		
	}
	
	public void startGame() {
		board = new QuestBoard(4, 4);
//		setupTeams();
		tmpSetupTeams(); // by default 1 team, (2 players in 1 team)
		setGamersQueue(getTeams());
		boolean gameStop = false;
		board.displayBoard();
		while (!gameStop) {
			Team team = (Team) getNextInQueue(mapTeams);
			char cellType = teamMove(team);
			System.out.println("0 CURRENT row,col: " +team.getCurrentRow() + " "+ team.getCurrentCol() + "" );
			board.displayBoard();
			cellTypeHandler(team, cellType);
			
//			for(Cell[] c: board.getBoard()) {
//				for (Cell cell : c) {
//					System.out.println("Cell type = " + cell + " " + cell.getTotalPieces());
//				}
//			}
			System.out.println("1 CURRENT row,col: " +team.getCurrentRow()  + " "+ team.getCurrentCol() + "" );
//			board.displayBoard();
			if ((team.getCurrentRow() == board.rows - 1) && (team.getCurrentCol() == board.cols - 1)) {
				gameStop = true;
				System.out.println("\nWinners");
			}
			
		}

	}

	public char teamMove(Team team) {
		char[] list = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i' };
		char input = Character.toUpperCase(InputHandler.getCharacter(list));
		char cellType = 0;
		switch (input) {
		case 'W':
//			System.out.println("Move up >>");	
			if (board.isValidMove(team.getCurrentRow()  - 1, team.getCurrentCol())) {
				cellType = board.makeMove(team, team.getCurrentRow() - 1, team.getCurrentCol());
			}
			break;
		case 'A':
//			System.out.println("Move left >>");
			if (board.isValidMove(team.getCurrentRow() , team.getCurrentCol() - 1)) {
				cellType = board.makeMove(team, team.getCurrentRow() , team.getCurrentCol() - 1);
			}
			break;
		case 'S':
//			System.out.println("Move down >>");
			if (board.isValidMove(team.getCurrentRow()  + 1, team.getCurrentCol())) {
				cellType = board.makeMove(team, team.getCurrentRow() + 1, team.getCurrentCol());
			}
			break;
		case 'D':
//			System.out.println("Move right >>");
			if (board.isValidMove(team.getCurrentRow(), team.getCurrentCol() + 1)) {
				cellType = board.makeMove(team, team.getCurrentRow(), team.getCurrentCol() + 1);
			}
			break;
		case 'Q':
			System.out.println("Quittting... user typed 'Q'");
			break;
		case 'I':
			System.out.println("Printing information... user typed 'I'");
			break;
		}
		return cellType;
	}
	/*
	 * Current cell further actions.
	 */
	public void cellTypeHandler(Team team, char cellType) {
		if (cellType == 'M') { // This cell contains monsters -> start fight
			initFight(team);
		} else if (cellType == '$') { // This cell is market -> start shopping/selling, if 'yes'
			for(Player player: team.getTeam()) {
				System.out.println(player + ", do you want to enter Market to purchase or sell items?");
				if (InputHandler.YesOrNo()) {
					enterMarket(player);
				}
			}
			
		} else {
			
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
			enterMarket(player);
		}
	}
	
	private void initFight(Team team) {
		Fight.startFightMessage();
		Fight.fight(team);
		System.out.println("Countdown ended. \nFight started in row,col: " +team.getCurrentRow() + " "+ team.getCurrentCol() + "" );

		board.removeMonstersFromCell(team.getCurrentRow(), team.getCurrentCol());
		System.out.println("Fight ended : " + board.getBoard()[team.getCurrentRow()][team.getCurrentCol()].getAllPieces());
		return;
	}

}
