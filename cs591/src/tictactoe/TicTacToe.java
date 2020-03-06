package tictactoe;

import java.util.ArrayList;
import java.util.List;

/*
 * The TicTacToe class models the TTT game itself. Handles classic mode and single/team mode based rules.
 */
public class TicTacToe extends Game {

	private static int MovesLeft = 9;
	private boolean teamMode = false;
	final static int TEAM_CAPACITY = 100; // maximum number of players in one team
	private List<Character> figures = new ArrayList<Character>();
	TicTacToeBoard board;

	/*
	 * setup Players for this TicTacToe game
	 */
	public void setupPlayers() {
		teamMode = false;
		int numPlayers;
		// Create n players
		System.out.println("How many players will be playing?");
		numPlayers = InputHandler.getInteger(2, board.getNumberCells() / 2 + 1);
		for (int i = 0; i < numPlayers; i++) {
			Player player = addPlayer();
			System.out.println("\n" + player + ", which figure/mark will you use? (e.g. X, O...)");
			char figure = chooseFigure();
			player.setPlayerPiece(new SimplePiece(figure));
			System.out.println(
					"\n" + player + " will play with '" + player.getPlayerPiece().getPieceFigure() + "' figure.\n");

		}
		System.out.print("\nDo you want to randomize turns between players?");
		if (InputHandler.YesOrNo()) {
			setGamersQueue(GenericMethods.shuffle(getPlayers()));
		} else {
			setGamersQueue(getPlayers());
		}
	}

	/*
	 * Setup Teams for this TicTacToe game
	 */
	public void setupTeams() {
		teamMode = true;
		int numTeams, numPlayers;

		System.out.println("How many teams will be playing?");
		numTeams = InputHandler.getInteger(2, board.maxSide());

		for (int id = 0; id < numTeams; id++) {
			Team t = new Team(id);
			System.out.printf(
					"How many players will be playing in team %d? Maximum number of players in one team is %d\n", id,
					TEAM_CAPACITY);
			numPlayers = InputHandler.getInteger(1, TEAM_CAPACITY);

			System.out.printf("\nTeam %d '%s', which figure/mark will you use? (e.g. X, O...)\n", id, t.getName());
			char figure = chooseFigure();

			t.setTeamPiece(new SimplePiece(figure));
			// TODO: Do you want to configure each player? (n/y)
			// Create and add m players into every team
			for (int i = 0; i < numPlayers; i++) {
				Player p = addPlayer();
				t.addPlayer(p);
				p.setTeamId(id);
			}
			t.setAllPlayersPiece();
			addTeam(t);
		}
		System.out.print("\n Do you want to randomize turns between teams?");
		if (InputHandler.YesOrNo()) {
			setGamersQueue(GenericMethods.shuffle(getTeams()));
		} else {
			setGamersQueue(getTeams());
		}
	}

	/*
	 * Start the TicTacToe game. Setup board dimension, strike number, and mode.
	 * Then setup players/teams. After this game ends calls endGameHandler().
	 */
	public void startGame() {
		board = new TicTacToeBoard();
		board.displayBoard();
		resetMovesLeftNumber();
		System.out.println("\nDo you want to play TicTacToe in teams?");
		if (InputHandler.YesOrNo()) {
			setupTeams();
			teamStyleTTT();
		} else {
			setupPlayers();
			System.out.println("\nDo you want to play TicTacToe with 'undo move' feature?");
			if(InputHandler.YesOrNo()) {
				singleStyleTTTundo();
			} else {
				singleStyleTTT();
			}
		}
		endGameHandler();
	}

	/*
	 * Play again the TicTacToe game with previous setup mode.
	 */
	public void playAgain() {
		board.resetBoard();
		resetMovesLeftNumber();
		if (teamMode) {
			teamStyleTTT();
		} else {
			singleStyleTTT();
		}
	}

	/*
	 * Reset this TicTacToe game with new players/teams.
	 */
	public void resetGame() {
		figures.clear();
		board.resetBoard();
		resetMovesLeftNumber();
		cleanupGamers();
	}

	/*
	 * Game rule for single players mode.
	 */
	public void singleStyleTTT() {
		board.displayBoard();

		boolean gameStop = false;
		while (!gameStop) {
//			Player p = getNextPlayer();
			Player p = (Player) getNextInQueue(mapPlayers);
			board.makeMove(p);
			MovesLeft--;
			board.displayBoard();

			if (p.isWinner()) {
				System.out.println("\nWinner is " + p + "!");
				gameStop = true;
				p.changeToNonWinner();
				break;
			}
			if (MovesLeft == 0) {
				System.out.println("It's a draw!");
				gameStop = true;
				break;
			}

		}
	}

	/*
	 * Game rule for team mode.
	 */
	public void teamStyleTTT() {

		board.displayBoard();
		boolean gameStop = false;
		while (!gameStop) {

			Team t = (Team) getNextInQueue(mapTeams);
			Player p = t.getNextTeamPlayer();

			board.makeMove(p);
			MovesLeft--;
			board.displayBoard();

			if (p.isWinner()) {
				System.out.println("\nWinner is " + t + "!");
				gameStop = true;
				p.changeToNonWinner();
				break;
			}

			if (MovesLeft == 0) {
				System.out.println("It's a draw!");
				break;
			}
		}
	}

	/*
	 * Classic TicTacToe game rules with two players X and O.
	 */
	public void classicStyleTTT() {

		board = new TicTacToeBoard(3, 3);
		resetMovesLeftNumber();

		Player player1 = addPlayer();
		Player player2 = addPlayer();
		player1.setPlayerPiece(new SimplePiece('X'));
		player2.setPlayerPiece(new SimplePiece('O'));
		setGamersQueue(getPlayers());
		
		System.out.print("\n Do you want to randomize turns?");
		if (InputHandler.YesOrNo()) {
			GenericDeque.setQueue(GenericMethods.shuffle(getPlayers()));
		} else {
			
			GenericDeque.setQueue(getPlayers());
		}

		singleStyleTTT();
	}
	/*
	 * Game rule for single players mode. With 'move undo' feature.
	 */
	public void singleStyleTTTundo() {
		board.displayBoard();

		boolean gameStop = false;
		Player curr_player = (Player) getNextInQueue(mapPlayers);
		board.makeMove(curr_player);
		MovesLeft--;
		board.displayBoard();
		
		while (!gameStop) {
			System.out.print("\nDo you want undo the move?");
			if(InputHandler.YesOrNo()) {
				//clear cell
				board.getBoard()[curr_player.getEnteredRow()][curr_player.getEnteredCol()].clearCell();
				board.makeMove(curr_player);
				MovesLeft--;
				board.displayBoard();
			} else {
				MovesLeft--;
				board.displayBoard();
				curr_player = (Player) getNextInQueue(mapPlayers);
				board.makeMove(curr_player);
			}
			
			if (curr_player.isWinner()) {
				board.displayBoard();
				System.out.println("\nWinner is " + curr_player + "!");
				gameStop = true;
				curr_player.changeToNonWinner();
				break;
			}
			if (MovesLeft == 0) {
				System.out.println("It's a draw!");
				gameStop = true;
				break;
			}
		}
	}
	/*
	 * Reset moves left to original.
	 */
	public void resetMovesLeftNumber() {
		MovesLeft = board.getNumberCells();
	}
	public void undoMove(Player player) {
		int row, col;
	}

	/*
	 * Choose figure for the team/player. Handle invalid/occupied figures.
	 */
	public char chooseFigure() {
		boolean isValid = false;
		char figure = 0;
		while (isValid == false) {
			figure = InputHandler.getCharacter();
			if (figures.contains(figure)) {
				System.out.println("This figure is already used. Pick another.");
			} else {
				isValid = true;
			}
		}
		figures.add(figure);
		return figure;
	}


	/*
	 * Return true if game played by teams.
	 */
	public boolean getTeamMode() {
		return teamMode;
	}

	/*
	 * Display results.
	 */
	public void displayResults() {
		System.out.println("Results:");
		if (getTeamMode()) {
			for (Integer t : listTeams) {
				Team team = getTeam(t);
				System.out.println(team + " won " + team.getTotalWins() + " round(s).");
			}
		} else {
			for (Integer p : listPlayers) {
				Player player = getPlayer(p);
				System.out.println(player + " won " + player.getTotalWins() + " round(s).");

			}
		}
	}


	/*
	 * Ask user to quit, continue this game, or play other game. Handle invalid
	 * inputs until correct input typed.
	 */
	public void endGameHandler() {
		System.out.print(
				"\nType 'Q' to quit the game. Type 'A' to play again. Type 'O' to play other games. Type 'W' to go back to Welcome page.\nType here: ");
		char[] list = new char[] { 'Q', 'q', 'A', 'a', 'O', 'o', 'C', 'c', 'W','w' };
		char input = Character.toUpperCase(InputHandler.getCharacter(list));

		switch (input) {
		case 'Q':
			
			quitGame();
		case 'A':
			System.out.println("\n\nYou decided to play again! Here we go...\n\n"
					+ "Do you want to play again with the same team/player setup?");
			if (InputHandler.YesOrNo()) {
				playAgain();
			} else {
				resetGame();
				startGame();
			}
			endGameHandler();
		case 'W':
			System.out.println("\n\nYou decided to go to welcome page! (Game history will be erased!)\n\n");
			Main.main(null);
		case 'O':
			System.out.println("\n!Coming soon...\nOther games are under construction!\n");
			endGameHandler();
		}
	}
}
