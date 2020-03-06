package quest;

/*
 * Rules for the game (flow)
 */
public class Quest extends Game {
	
	static double chanceToMeetMonsters = 0.8;
	QuestBoard board;
	public int current_row = 0, current_col = 0;
	Piece teamPiece = new SimplePiece('@');
	

	public void setupPlayers() {
		Team t = new Team(0);
		System.out.println("How many players will be playing?");
		int numPlayers = InputHandler.getInteger(1, 3);
		for (int i = 0; i < numPlayers; i++) {
			Player player = addPlayer();
			System.out.println("\n" + player + ", choose your hero character: ");
			CSVReader.displayAllHeroesData();
			int index = InputHandler.getInteger(0, CSVReader.heroes.size() - 1);
			player.setHero(CSVReader.retrieveHero(index));
			System.out.println("\n" + player + " is " + player.getHero());
			t.addPlayer(player);
		}
		addTeam(t);
	}

	public void startGame() {
		board = new QuestBoard(8, 8);
		setupPlayers();
		
		boolean gameStop = false;
		while (!gameStop) {
			board.displayBoard();
			playerMove();
			if ((current_row == board.rows-1) && (current_col == board.cols-1)) {
				gameStop = true;
				System.out.println("\nWinners" );
			}
		}
		
	}

	public void playerMove() {
		char[] list = { 'W', 'w', 'A', 'a', 'S', 's', 'D', 'd', 'Q', 'q', 'I', 'i' };
		char input = Character.toUpperCase(InputHandler.getCharacter(list));
		char cell = 0;
		System.out.println("Input " + input);
		switch (input) {
		case 'W':
//			System.out.println("Move up >>");			
			if (board.isValidMove(current_row - 1, current_col)) {
				cell = board.makeMove(current_row, current_col, current_row - 1, current_col, teamPiece);
				current_row--;
			}break;
		case 'A':
//			System.out.println("Move left >>");
			if (board.isValidMove(current_row, current_col - 1)) {
				cell = board.makeMove(current_row, current_col, current_row, current_col - 1, teamPiece);
				current_col--;
			}break;
		case 'S':
//			System.out.println("Move down >>");
			if (board.isValidMove(current_row + 1, current_col)) {
				cell = board.makeMove(current_row, current_col, current_row + 1, current_col, teamPiece);
				current_row++;
			}break;
		case 'D':
//			System.out.println("Move right >>");
			if (board.isValidMove(current_row, current_col + 1)) {
				cell = board.makeMove(current_row, current_col, current_row, current_col + 1, teamPiece);
				current_col++;
			} break;
		case 'Q':
			break;
		case 'I':
			playerMove();
			break;
		}
		if (cell == 'M') {
			board.getBoard()[current_row][current_col].placePiece(new SimplePiece('M'));
			initFight();
		} else if (cell == '$') {
			System.out.println("Do you want to enter Market?");
			if(InputHandler.YesOrNo()) {
				enterMarket();
			}
		}
		return;
	}
	
	private void enterMarket() {
		
		// TODO Auto-generated method stub
		
	}
	private void initFight() {
		// TODO Auto-generated method stub
		
	}

}
