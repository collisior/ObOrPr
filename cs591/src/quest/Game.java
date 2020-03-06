package quest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
 * The Game class models the general game structure.
 */
public abstract class Game {

	protected HashMap<Integer, Player> mapPlayers = new HashMap<>(); // HashMap for faster search/access serves like Database
	protected HashMap<Integer, Team> mapTeams = new HashMap<>();// HashMap for faster search/access serves like Database
	protected ArrayList<Integer> listTeams = new ArrayList<Integer>(); // list of all teams IDs playing current game
	protected ArrayList<Integer> listPlayers = new ArrayList<Integer>(); // List of all players IDs playing current game
	@SuppressWarnings("rawtypes")
	protected Deque queue; //queue of current game. Call setGamersQueue()to activate this Deque
	private int availableId = 1;

	/*
	 * Return available id.
	 */
	public int getAvailableId() {
		return this.availableId;
	}

	/*
	 * Update available id.
	 */
	public void updateAvailableId() {
		this.availableId++;
	}

	/*
	 * Create new Player and return this Player
	 */
	public Player addPlayer() {
		Player p = new Player(this.availableId);
		mapPlayers.put(this.availableId, p);
		updateAvailableId();
		listPlayers.add(p.getId());
		return p;
	}

	/*
	 * Delete given player (remove from HashMap storage)
	 */
	public void deletePlayer(int id) {
		mapPlayers.remove(id);
		listPlayers.remove(id);
	}

	/*
	 * Delete given team and all of its players
	 */
	public void deleteTeam(int id) {
		Team t = mapTeams.get(id);
		ArrayList<Player> team = t.getTeam();
		for (Player p : team) {
			t.removePlayer(p);
			deletePlayer(p.getId());
		}
		mapTeams.remove(id);
		listTeams.remove(id);
	}

	/*
	 * Add given team to hashmap storage.
	 */
	public void addTeam(Team t) {
		listTeams.add(t.getId());
		mapTeams.put(t.getId(), t);
	}

	/*
	 * Returns list of all teams id playing this Game
	 */
	public ArrayList<Integer> getTeams() {
		return this.listTeams;
	}
	/*
	 * Clean up players/teams from this current game
	 */
	public void cleanupGamers() {
		listTeams.clear();
		listPlayers.clear();
	}

	/*
	 * Returns list of all players id playing this Game
	 */
	public HashMap<Integer, Player> getMapPlayers() {
		return this.mapPlayers;
	}
	/*
	 * Returns list of all teams id playing this Game
	 */
	public HashMap<Integer, Team> getMapTeams() {
		return this.mapTeams;
	}

	/*
	 * Returns list of all players id playing this Game
	 */
	public ArrayList<Integer> getPlayers() {
		return this.listPlayers;
	}
	/*
	 * Returns Team via given id
	 */
	public Team getTeam(int id) {
		return mapTeams.get(id);
	}

	/*
	 * Returns Player via given id
	 */
	public Player getPlayer(int id) {
		return mapPlayers.get(id);
	}
	/*
	 * Returns item from given HashMap by given key
	 */
	public <T> T getItem(HashMap<?, T> map, Object key) {
		return map.get(key );
	}
	/*
	 * Returns object (team/player) with previous turn. And put this item to the front.
	 */
	@SuppressWarnings("unchecked")
	public <T> Object getPrevInQueue(HashMap<Integer, T> map) {
		Object teamId = GenericDeque.pollFirst(queue);
		queue = GenericDeque.enqueue(queue, teamId); // put item back to the queue
		return getItem(map, teamId); //find item in map
	}
	/*
	 * Returns object (team/player) whose next turn. And put item to the end of this queue.
	 */
	@SuppressWarnings("unchecked")
	public <T> Object getNextInQueue(HashMap<Integer, T> map) {
		Object id = GenericDeque.pollFirst(queue);
		queue = GenericDeque.enqueue(queue, id); // put item back to the queue
		return getItem(map, id); //find item in map
	}
	/*
	 * Set queue of given list (players/teams).
	 */
	public void setGamersQueue(ArrayList<Integer> array) {
		this.queue = GenericDeque.setQueue(array);
	}

	/*
	 * Ask user to Pick a game or exit. Return user's choice.
	 */
	public static char ChooseGame() {
		// allowedChars: list of chars representing games
		char[] existingGames = new char[] { '0', '1', 'Q' };
		System.out.println("Pick a game you want to play...Type input options (Very case sensitive!):\n"
				+ "'0' - TicTacToe (personalize dimension, strike number, teams, and players)\n"
				+ "'1' - Classic 3x3 TicTacToe with 'X' and 'O'.\n" + "'Q' to quit\n");

		char input = InputHandler.getCharacter(existingGames);

		return input;
	}

	public void startGame() {
		// abstract startGame();
	}

	public void displayResults() {

	}

	public void quitGame() {
		displayResults();
		System.out.println("\nThank you for playing! Have a noice day!");
		System.exit(0);
	}

}
