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
		team.addPlayer(player);
		SetupQuestHelper.setupTeam(team);
		team.setPiece(new SimplePiece('T'));
		addTeam(team);
		board.getBoard()[0][0].placePiece(team.getPiece());
		System.out.println("\nTeam piece = " +team.getPiece()+ "\n");
		
	}
	
	public void startGame() {
		board = new QuestBoard(4, 4);
//		setupTeams();
		tmpSetupTeams(); // by default 1 team, (1 players in 1 team)
		setGamersQueue(getTeams());
		boolean gameStop = false;
		board.displayBoard();
		while (!gameStop) {
			Team team = (Team) getNextInQueue(mapTeams);
			char cellType = teamMove(team);
			System.out.println("CURRENT row,col: " +team.current_row + " "+ team.current_col + "" );
			board.displayBoard();
			cellTypeHandler(team, cellType);
//			for(Cell[] c: board.getBoard()) {
//				for (Cell cell : c) {
//					System.out.println("Cell type = " + cell + " " + cell.getTotalPieces());
//				}
//			}
			board.displayBoard();
			if ((team.current_row == board.rows - 1) && (team.current_col == board.cols - 1)) {
				gameStop = true;
				System.out.println("\nWinners");
			}
			
		}

	}

	public char teamMove(Team team) {
		char[] list = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i' };
		char input = Character.toUpperCase(InputHandler.getCharacter(list));
		char cell = 0;
		switch (input) {
		case 'W':
//			System.out.println("Move up >>");	
			if (board.isValidMove(team.current_row - 1, team.current_col)) {
				cell = board.makeMove(team, team.current_row - 1, team.current_col);
				team.current_row--;
			}
			break;
		case 'A':
//			System.out.println("Move left >>");
			if (board.isValidMove(team.current_row, team.current_col - 1)) {
				cell = board.makeMove(team, team.current_row, team.current_col - 1);
				team.current_col--;
			}
			break;
		case 'S':
//			System.out.println("Move down >>");
			if (board.isValidMove(team.current_row + 1, team.current_col)) {
				cell = board.makeMove(team, team.current_row + 1, team.current_col);
				team.current_row++;
			}
			break;
		case 'D':
//			System.out.println("Move right >>");
			if (board.isValidMove(team.current_row, team.current_col + 1)) {
				cell = board.makeMove(team, team.current_row, team.current_col + 1);
				team.current_col++;
			}
			break;
		case 'Q':
			break;
		case 'I':
			System.out.println("Printing information... user typed 'I'");
			break;
		}
		return cell;
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
		System.out.println(player + ", do you want to purchase items Market?");
		if (InputHandler.YesOrNo()) {
			player.getHero().sellFromStorage();
			
		}
		System.out.println(player + ", do you want sell your items for the half of the price?");
		if (InputHandler.YesOrNo()) {
//			Market.purchaseFromMarket(player);
		}
		System.out.println(player + ", do you want exit Market? (Type 'N' if you want to continue shopping/selling)");
		if (InputHandler.YesOrNo()) {
			enterMarket(player);
		}
	}
	
	private void initFight(Team team) {
		// TODO Auto-generated method stub
		System.out.println("Fight started in row,col: " +team.current_row + " "+ team.current_col + "" );
		board.getBoard()[team.current_row][team.current_col].removePiece(new SimplePiece('M'));
		System.out.println(" initFight : " + board.getBoard()[team.current_row][team.current_col].getTotalPieces());
		return;

	}

}
